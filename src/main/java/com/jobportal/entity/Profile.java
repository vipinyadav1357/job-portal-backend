package com.jobportal.entity;

import com.jobportal.dtos.Certification;
import com.jobportal.dtos.Experience;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Document(collection = "profiles")
public class Profile {
    @Id
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String company;
    private String location;
    private byte[] profilePicture;
    private String about;

    private List<String> skills;

    private List<Experience> experience;

    private List<Certification> certifications;

}
