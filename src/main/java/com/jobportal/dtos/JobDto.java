package com.jobportal.entity;

import com.jobportal.dtos.Applicants;
import com.jobportal.enums.JobStatus;
import com.jobportal.enums.JobType;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class JobDto {
    private Long id;
    private String jobTitle;
    private String company;
    private List<Applicants> applicants;
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
