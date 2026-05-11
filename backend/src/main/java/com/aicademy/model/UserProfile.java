package com.aicademy.model;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record UserProfile(
        @NotBlank String experience,
        @NotBlank String jobRole,
        List<String> interests,
        List<String> goals,
        String learningStyle,
        Integer weeklyHours
) {
}
