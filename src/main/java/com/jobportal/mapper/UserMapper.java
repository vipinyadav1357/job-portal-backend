package com.jobportal.mapper;

import com.jobportal.dtos.RegisterRequest;
import com.jobportal.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;
    public User toUser(RegisterRequest dto){
        return User.builder().id(dto.getId()).profileId(dto.getProfileId()).name(dto.getName()).email(dto.getEmail()).password(passwordEncoder.encode(dto.getPassword())).accountType(dto.getAccountType()).build();
    }
    public RegisterRequest toUserDto(User user){
        return RegisterRequest.builder().id(user.getId()).profileId(user.getProfileId()).name(user.getName()).email(user.getEmail()).password(user.getPassword()).accountType(user.getAccountType()).build();
    }
}
