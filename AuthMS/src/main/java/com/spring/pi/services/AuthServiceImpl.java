package com.spring.pi.services;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.ERole;
import com.spring.pi.entities.Role;
import com.spring.pi.payload.request.LoginRequest;
import com.spring.pi.payload.request.ResetPasswordRequest;
import com.spring.pi.payload.request.SignupRequest;
import com.spring.pi.payload.response.JwtResponse;
import com.spring.pi.payload.response.MessageResponse;
import com.spring.pi.repositories.ActorRepository;
import com.spring.pi.repositories.RoleRepository;
import com.spring.pi.security.jwt.JwtUtils;
import com.spring.pi.security.services.UserDetailsImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{

    AuthenticationManager authenticationManager;

    ActorRepository actorRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtUtils jwtUtils;

    public Actor findActorById( Long id){
    return actorRepository.findById(id).orElse(null);
    }

    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        if (actorRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (actorRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        Actor actor = new Actor(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "exp":
                        Role expRole = roleRepository.findByName(ERole.ROLE_EXPERT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(expRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        actor.setRoles(roles);
        actorRepository.save(actor);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @Override
    public void updateResetPasswordTokenEmail(ResetPasswordRequest resetPasswordRequest) throws Exception  {
        Actor actor = actorRepository.findActorByEmail(resetPasswordRequest.getMail());
        if (actor != null) {
            actor.setResetPasswordToken(resetPasswordRequest.getToken());
            actorRepository.save(actor);
        } else {
            throw new Exception("Could not find any customer with the email " + resetPasswordRequest.getPhone());
        }

    }

    @Override
    public void updateResetPasswordTokenPhone(ResetPasswordRequest resetPasswordRequest) throws Exception  {
        Actor actor = actorRepository.findActorByPhone(resetPasswordRequest.getPhone());
        if (actor != null) {
            actor.setResetPasswordToken(resetPasswordRequest.getToken());
            actorRepository.save(actor);
        } else {
            throw new Exception("Could not find any customer with the phone " + resetPasswordRequest.getPhone());
        }
    }


    public Actor getByResetPasswordToken(String token) {
        return actorRepository.findActorByResetPasswordToken(token);
    }

    public void updatePassword(Actor actor, String newPassword) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(newPassword);
        actor.setPassword(encodedPassword);

        actor.setResetPasswordToken(null);
        actorRepository.save(actor);
    }
}
