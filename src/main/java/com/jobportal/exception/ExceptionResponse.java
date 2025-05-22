package com.jobportal.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class ExceptionResponse {
    private Integer bussinessErrorCode;
    private String bussinessErrorDescription;
    private String error;
    private Set<String> validationErrors;
    private Map<String, String> errors;
}
