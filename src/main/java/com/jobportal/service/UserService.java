package com.jobportal.service;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.AuthenticationResponse;
import com.jobportal.dtos.Response;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.exception.JobPortalException;
import jakarta.mail.MessagingException;
import org.springframework.security.core.Authentication;

public interface UserService {
     RegisterRequest registerUser(RegisterRequest dto) throws JobPortalException;
     AuthenticationResponse loginUser(AuthenticationRequest dto) throws JobPortalException;
     Response sendOtp(String email, Authentication authentication) throws JobPortalException, MessagingException;
     Response verifyOtp(String email, String otp, Authentication authentication) throws JobPortalException, MessagingException;
     Response changePass(AuthenticationRequest request, Authentication authentication) throws JobPortalException;
}
