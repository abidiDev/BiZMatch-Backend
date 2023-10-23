package com.spring.pi.controllers;

import javax.validation.Valid;

import com.spring.pi.entities.Actor;
import com.spring.pi.entities.notMapped.EmailDetails;
import com.spring.pi.payload.request.*;
import com.spring.pi.payload.response.MessageResponse;
import com.spring.pi.services.AuthService;
import com.spring.pi.services.ContactingService;
import com.spring.pi.services.IService;
import lombok.AllArgsConstructor;
import net.bytebuddy.utility.RandomString;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.*;

;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {
  AuthService authService;
  IService iService;
  JavaMailSender mailSender;
  ContactingService contactingService;
  @GetMapping(path = "actorById/{id}")
  public Actor findActorById(@PathVariable Long id){
    return  authService.findActorById(id);

  }
  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    return authService.authenticateUser(loginRequest);
  }
  @PostMapping("/addClient")
  public ResponseEntity<?> addClient( @RequestBody ClientRequest actor) {

    return iService.addActor(actor);
  }
  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
    return authService.registerUser(signUpRequest);
  }
@PostMapping("/partnershipRequest")
public ResponseEntity<?> PartnershipEmail(@RequestBody PartnershipdRequest partnershipRequest) {
  try {

    EmailDetails ed=new EmailDetails();
    Actor a= authService.findActorById(partnershipRequest.getRecipientID());

    ed.setRecipient(a.getEmail());
    ed.setSubject("Partnership request from BIZZMATCH");
    String content= "<html>"
            + "<body>"
            + "<div style=\"background-color: #f9f9f9; padding: 20px;\">"
            + "<img src=\"https://zupimages.net/up/23/39/oben.jpg\"  style=\"display: block; margin: 0 auto;\" />"
            + "<p>Hello,</p>"
            + "<p>You have requested to create Partnership.</p>"
            + "<p>Click the link below to change your password:</p>"
            + "<p>+ resetPasswordLink </p>"
            + "<br>"
            + "<p>Ignore this email if you do remember your password, "
            + "or you have not made the request.</p>"
            + "</div>"
            + "</body>"
            + "</html>";
    ed.setMsgBody(content);
    contactingService.sendSimpleMail(ed);
    return ResponseEntity.ok(new MessageResponse("we have send a link to rset your paswword please check it  !"));

  } catch (Exception ex) {
    return ResponseEntity
            .badRequest()
            .body(new MessageResponse("you have writen a wrong email!"));
  }
}

  @PostMapping("/forgot_passworddemondByEmail")
  public ResponseEntity<?> forgotPasswordDemondByEmail(@RequestBody ResetPasswordRequest resetPasswordRequest) {

    String token = RandomString.make(30);
    String msg;
    resetPasswordRequest.setToken(token);

    try {
      authService.updateResetPasswordTokenEmail(resetPasswordRequest);
      String resetPasswordLink =  "http://localhost:4200/vitrine/ResetFormComponent?token=" + token;

      EmailDetails ed=new EmailDetails();
      ed.setRecipient(resetPasswordRequest.getMail());
      ed.setSubject("Reset password Link from BIZZMATCH");
      String content= "<html>"
              + "<body>"
              + "<div style=\"background-color: #f9f9f9; padding: 20px;\">"
              + "<img src=\"https://zupimages.net/up/23/39/oben.jpg\"  style=\"display: block; margin: 0 auto;\" />"
              + "<p>Hello,</p>"
              + "<p>You have requested to reset your password.</p>"
              + "<p>Click the link below to change your password:</p>"
              + "<p><a href=\"" + resetPasswordLink + "\">Change my password</a></p>"
              + "<br>"
              + "<p>Ignore this email if you do remember your password, "
              + "or you have not made the request.</p>"
              + "</div>"
              + "</body>"
              + "</html>";
      ed.setMsgBody(content);
      contactingService.sendSimpleMail(ed);
      return ResponseEntity.ok(new MessageResponse("we have send a link to rset your paswword please check it  !"));

    } catch (Exception ex) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("you have writen a wrong email!"));
    }


  }





  @PostMapping("/reset_password")
  public ResponseEntity<?> processResetPassword(@RequestBody ResetPasswordRequest resetPasswordRequest) {

    String msg;
    Actor actor = authService.getByResetPasswordToken(resetPasswordRequest.getToken());

    if (actor == null) {
      return ResponseEntity
              .badRequest()
              .body(new MessageResponse("you haven't access!"));    } else {
      authService.updatePassword(actor, resetPasswordRequest.getPassword());

      return ResponseEntity.ok(new MessageResponse("wpassword updated successfully !"));

    }

  }
}