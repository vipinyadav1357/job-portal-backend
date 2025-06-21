package com.jobportal.controller;

import com.jobportal.dtos.ProfileDto;
import com.jobportal.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/profile")
@RequiredArgsConstructor
@Validated
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/get/{id}")
    public ResponseEntity<ProfileDto> getProfile(@PathVariable Long id, Authentication authentication){
        return ResponseEntity.ok(profileService.getProfile(id));
    }
}
