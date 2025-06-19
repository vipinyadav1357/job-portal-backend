package com.jobportal.service;

import com.jobportal.enums.EmailTemplateName;
import jakarta.mail.MessagingException;
import org.springframework.scheduling.annotation.Async;

public interface EmailService {
    @Async
     void sendEmail(String to, String username, EmailTemplateName emailTemplateName, String confirmationUrl,
                          String activationCode, String subject) throws MessagingException;
}
