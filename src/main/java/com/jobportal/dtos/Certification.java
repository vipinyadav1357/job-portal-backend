package com.jobportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Certification {
    private String name;
    private String issuer;
    private LocalDateTime issueDate;
    private String certificateId;
    private String location;
}
