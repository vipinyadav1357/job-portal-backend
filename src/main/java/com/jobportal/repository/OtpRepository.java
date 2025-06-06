package com.jobportal.repository;

import com.jobportal.entity.OTP;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface OtpRepository extends MongoRepository<OTP, String> {
    Optional<OTP> findByOtpCode(String otp);
}
