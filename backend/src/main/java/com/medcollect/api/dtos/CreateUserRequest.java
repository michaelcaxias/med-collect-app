package com.medcollect.api.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;

@Builder
public record CreateUserRequest(
    @NotBlank(message = "firebaseUid is required")
    String firebaseUid,
    
    @NotBlank(message = "name is required")
    String name
) {}
