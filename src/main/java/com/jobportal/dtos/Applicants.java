package com.jobportal.dtos;

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
public class Applicants {
    private Long applicantId;
    private LocalDateTime appliedTime;
    private ApplicationStatus applicationStatus;
}
