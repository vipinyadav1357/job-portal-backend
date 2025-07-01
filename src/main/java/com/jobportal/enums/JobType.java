package com.jobportal.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JobType {
    FULL_TIME("Full Time"),PART_TIME("Part Time"),CONTRACT("Contract"),FREELANCE("Freelance"),INTERNSHIP("Internship");
    private final String type;

    @JsonValue
    public String getType() {
        return type;
    }

    @JsonCreator
    public static JobType fromValue(String value) {
        for (JobType jobType : JobType.values()) {
            if (jobType.getType().equalsIgnoreCase(value)) {
                return jobType;
            }
        }
        throw new IllegalArgumentException("Invalid JobType: " + value);
    }
}
