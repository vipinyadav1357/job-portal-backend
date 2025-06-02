package com.jobportal.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Async
    public String sendEmail(){
        return null;
    }
}
