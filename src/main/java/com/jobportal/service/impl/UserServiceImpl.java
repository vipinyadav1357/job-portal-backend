package com.jobportal.service.impl;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.OtpResponse;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.EmailService;
import com.jobportal.service.UserService;
import com.jobportal.utils.SequenceUtilities;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public RegisterRequest registerUser(RegisterRequest dto) throws JobPortalException {
        Optional<User> user1 = userRepository.findByEmail(dto.getEmail());
        if (user1.isPresent())
            throw new JobPortalException("User Already Registered");
        dto.setId(SequenceUtilities.getNextSequence("users"));
        User user = userMapper.toUser(dto);
        return userMapper.toUserDto(userRepository.save(user));
    }

    @Override
    public AuthenticationRequest loginUser(AuthenticationRequest dto) throws JobPortalException {
        var user1 = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new JobPortalException(dto.getEmail() + " is not found "));
        return passwordEncoder.matches(dto.getPassword(), user1.getPassword()) ? dto : new AuthenticationRequest();
    }

    @Override
    public OtpResponse sendOtp(String email, Authentication authentication) throws JobPortalException {
        var user=(User)authentication.getPrincipal();
        if(email.equalsIgnoreCase(user.getEmail())){
           sendOtpAtEmail(user);
        }

        return new OtpResponse();
    }
    private void sendOtpAtEmail(User user){
       //this method will send otp
        emailService.sendEmail();
    }
}
