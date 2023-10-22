package com.spring.pi.services;


import com.spring.pi.entities.Actor;
import com.spring.pi.payload.request.LoginRequest;
import com.spring.pi.payload.request.ResetPasswordRequest;
import com.spring.pi.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;


public interface AuthService {


    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) ;
    public void updateResetPasswordTokenEmail(ResetPasswordRequest resetPasswordRequest) throws Exception;
    public void updateResetPasswordTokenPhone(ResetPasswordRequest resetPasswordRequest) throws Exception;

    public Actor getByResetPasswordToken(String token);
    public void updatePassword(Actor actor, String newPassword);

}
