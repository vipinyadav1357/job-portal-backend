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
public class Application {
    private Long jobId;
    private Long applicantId;
    private Long userId;
    private LocalDateTime interViewTime;
    private ApplicationStatus applicationStatus;
}
