package com.jobportal.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {
    FULL_TIME("full time"),PART_TIME("part time");
    private final String jobType;
}
