package com.jobportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProfileDto {
    private Long id;

    private String email;
    private String jobTitle;
    private String company;
    private String location;

    private String about;

    private List<String> skills;

    private List<Experience> experience;

    private List<Certification> certifications;

}
