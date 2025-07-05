package com.jobportal.dtos;

import com.jobportal.enums.JobStatus;
import com.jobportal.enums.JobType;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JobDto {
    private Long id;
    private Long postedBy;
    private String jobTitle;
    private String company;
    private List<ApplicantDto> applicants;
    private String about;
    private String experience;
    private JobType jobType;
    private String location;
    private Long packageOffered;
    private LocalDateTime postTime;
    private String description;
    private List<String> skillRequired;
    private JobStatus jobStatus;
}
