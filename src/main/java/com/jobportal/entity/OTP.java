package com.jobportal.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Document(collection = "otps")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
public class OTP {
    @Id
    private String email;
    private String otpCode;
    private LocalDateTime creationDate;
    private LocalDateTime updationTime;
    private LocalDateTime expiritionTime;
}
