package com.jobportal.entity;

import com.jobportal.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Applicant {
    private Long applicantId;
    private String name;
    private String email;
    private String contactNumber;
    private String portfolioLink;
    private byte[] resume;
    private String coverLetter;
    private LocalDateTime appliedTime;
    private ApplicationStatus applicationStatus;
}
