package com.jobportal.dtos;

import lombok.*;

@Data
@RequiredArgsConstructor
@Builder
public class AuthenticationResponse {
    private final String jwt;
}
