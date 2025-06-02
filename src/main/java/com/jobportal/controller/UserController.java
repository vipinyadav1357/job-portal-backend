package com.jobportal.controller;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.OtpResponse;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<RegisterRequest> registerUser(@RequestBody @Valid RegisterRequest dto) {

        return ResponseEntity.ok(userService.registerUser(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationRequest> loginUser(@RequestBody @Valid AuthenticationRequest dto) {

        return ResponseEntity.ok(userService.loginUser(dto));
    }

    @PostMapping("/otp/{email}")
    public ResponseEntity<OtpResponse> sendOtp(@PathVariable String email, Authentication authentication) {
        return ResponseEntity.ok(userService.sendOtp(email,authentication));
    }
}
