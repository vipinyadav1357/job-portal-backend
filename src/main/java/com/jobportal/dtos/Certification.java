package com.jobportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Certification {
    private String title;
    private String issuer;
    private String issueDate;
    private String certificateId;
    private String location;
}
