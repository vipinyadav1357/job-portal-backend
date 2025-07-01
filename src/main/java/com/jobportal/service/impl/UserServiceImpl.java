package com.jobportal.service.impl;

import com.jobportal.dtos.AuthenticationRequest;
import com.jobportal.dtos.RegisterRequest;
import com.jobportal.dtos.Response;
import com.jobportal.entity.OTP;
import com.jobportal.entity.User;
import com.jobportal.enums.EmailTemplateName;
import com.jobportal.exception.JobPortalException;
import com.jobportal.mapper.UserMapper;
import com.jobportal.repository.OtpRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.EmailService;
import com.jobportal.service.ProfileService;
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


@Service(value = "user_service")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService  {

    private final UserMapper userMapper;
    private final OtpRepository otpRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final ProfileService profileService;

    @Override
    public RegisterRequest registerUser(RegisterRequest dto) throws JobPortalException {
        Optional<User> user1 = userRepository.findByEmail(dto.getEmail());
        if (user1.isPresent())
            throw new JobPortalException("User Already Registered");
        dto.setId(SequenceUtilities.getNextSequence("users"));
        dto.setProfileId(profileService.createProfile(dto.getEmail()));
        return userMapper.toUserDto(userRepository.save(userMapper.toUser(dto)));
    }

    @Override
    public AuthenticationRequest loginUser(AuthenticationRequest dto) throws JobPortalException {
        var user = userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new JobPortalException(dto.getEmail() + " is not found "));
        if(passwordEncoder.matches(dto.getPassword(), user.getPassword())){
            return dto;
        }else{
            throw new JobPortalException("username or password is wrong");
        }
    }

    @Override
    public Response sendOtp(String email, Authentication authentication) throws JobPortalException, MessagingException {
//        var user = (User) authentication.getPrincipal();
        User user=userRepository.findByEmail(email).orElseThrow();
        if (email.equalsIgnoreCase(user.getEmail())) {
            sendOtpAtEmail(user);
        }
        return  Response.builder().message("Otp Sent to your Email Id").build();
    }


    private void sendOtpAtEmail(User user) throws MessagingException {
        //this method will send otp
        var otp = generateAndSaveActivationToken(user);
        emailService.sendEmail(user.getEmail(), user.getName(), EmailTemplateName.ACTIVATE_ACCOUNT, "", otp, "Account Activation");
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
    public Response verifyOtp(String email, String otp, Authentication authentication) throws JobPortalException, MessagingException {
        //        var user = (User) authentication.getPrincipal();
        User user=userRepository.findByEmail(email).orElseThrow();
        if (email.equalsIgnoreCase(user.getEmail())) {
            OTP loadedOTP = otpRepository.findByOtpCode(otp).orElseThrow(() -> new JobPortalException("Otp is wrong"));
            if (loadedOTP.getOtpCode().equals(otp) && LocalDateTime.now().isAfter(loadedOTP.getExpiritionTime())) {
                otpRepository.delete(loadedOTP);
                sendOtpAtEmail(user);
                throw new JobPortalException("otp has been expired");
            } else if (loadedOTP.getOtpCode().equals(otp)) {
                loadedOTP.setUpdationTime(LocalDateTime.now());
                otpRepository.save(loadedOTP);
            }
        } else {
            throw new JobPortalException("is that you ?");
        }
        return new Response("otp verified");
    }

    @Override
    public Response changePass(AuthenticationRequest request, Authentication authentication) throws JobPortalException {
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() -> new JobPortalException("user not found with this email"));
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        return new Response("password changed");
    }
}
