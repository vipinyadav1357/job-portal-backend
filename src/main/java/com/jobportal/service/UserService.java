package com.jobportal.service;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.OtpResponse;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.exception.JobPortalException;
import jakarta.mail.MessagingException;
import org.springframework.security.core.Authentication;

public interface UserService {
     RegisterRequest registerUser(RegisterRequest dto) throws JobPortalException;
     AuthenticationRequest loginUser(AuthenticationRequest dto) throws JobPortalException;
     OtpResponse sendOtp(String email, Authentication authentication) throws JobPortalException, MessagingException;
     OtpResponse verifyOtp(String email, String otp, Authentication authentication) throws JobPortalException, MessagingException;
}
