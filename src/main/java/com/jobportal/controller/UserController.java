package com.jobportal.controller;

import com.jobportal.dtos.UserDto;
import com.jobportal.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserDto dto){

    return ResponseEntity.ok(userService.registerUser(dto));
}
}
