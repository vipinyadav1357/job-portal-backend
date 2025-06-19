package com.jobportal.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Experience {
    private String title;
    private String company;
    private String location;
    private String startDate;
    private String endDate;
    private boolean working;

    private String description;
}
