package com.jobportal.controller;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.AuthenticationResponse;
import com.jobportal.dtos.Response;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.service.UserService;
import jakarta.mail.MessagingException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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
    public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody @Valid AuthenticationRequest dto) {
        return ResponseEntity.ok(userService.loginUser(dto));
    }

    @PostMapping("/otp/{email}")
    public ResponseEntity<Response> sendOtp(@PathVariable String email, Authentication authentication) throws MessagingException {
        return ResponseEntity.ok(userService.sendOtp(email,authentication));
    }
    @GetMapping("/otp/{email}/{otp}")
    public ResponseEntity<Response> verifyOtp(@PathVariable String email, @PathVariable String otp, Authentication authentication) throws MessagingException {
        return ResponseEntity.ok(userService.verifyOtp(email,otp,authentication));
    }
    @PostMapping("/changePass")
    public ResponseEntity<Response> changePass(@RequestBody @Valid AuthenticationRequest request, Authentication authentication) throws MessagingException {
        return ResponseEntity.ok(userService.changePass(request,authentication));
    }
}
