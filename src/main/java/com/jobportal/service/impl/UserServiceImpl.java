package com.jobportal.service.impl;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.OtpResponse;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.entity.OTP;
import com.jobportal.entity.User;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.OtpRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.UserService;
import com.jobportal.utils.SequenceUtilities;
import jakarta.mail.MessagingException;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;


@Service(value = "userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final OtpRepository otpRepository;
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
    public OtpResponse sendOtp(String email, Authentication authentication) throws JobPortalException, MessagingException {
        var user = (User) authentication.getPrincipal();
        if (email.equalsIgnoreCase(user.getEmail())) {
            sendOtpAtEmail(user);
        }
        return new OtpResponse("Otp Sent to your Email Id");
    }


    private void sendOtpAtEmail(User user) throws MessagingException {
        //this method will send otp
        var otp = generateAndSaveActivationToken(user);
        emailService.sendEmail(user.getEmail(), user.getName(), EMailTemplateName.ACTIVATE_ACCOUNT, "", otp, "Account Activation");
    }

    private @NotNull String generateAndSaveActivationToken(User user) {
        String generatedToken = generateActivationCode(6);

        var token = OTP.builder().email(user.getEmail()).otpCode(generatedToken).creationDate(LocalDateTime.now())
                .expiritionTime(LocalDateTime.now().plusMinutes(15)).build();

        otpRepository.save(token);
        return generatedToken;
    }

    private String generateActivationCode(int length) {
        String character = "0123456789";
        StringBuilder codeBuilder = new StringBuilder();
        SecureRandom secureRandom = new SecureRandom();
        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(character.length());
            codeBuilder.append(character.charAt(randomIndex));
        }
        return codeBuilder.toString();
    }

    @Override
    public OtpResponse verifyOtp(String email, String otp, Authentication authentication) throws JobPortalException, MessagingException {
        var user = (User) authentication.getPrincipal();
        if (email.equalsIgnoreCase(user.getEmail())) {
            OTP loadedOTP = otpRepository.findByOtp(otp).orElseThrow(() -> new JobPortalException("Otp is wrong"));
            if (loadedOTP.getOtpCode().equals(otp) && LocalDateTime.now().isAfter(loadedOTP.getExpiritionTime())) {
                otpRepository.delete(loadedOTP);
                sendOtpAtEmail(user);
                throw new JobPortalException("otp has been expired");
            }else if(loadedOTP.getOtpCode().equals(otp)){
                loadedOTP.setUpdationTime(LocalDateTime.now());
                otpRepository.save(loadedOTP);
            }
        } else {
            throw new JobPortalException("is that you ?");
        }
        return new OtpResponse("otp verified");
    }
}
