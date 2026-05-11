package com.aicademy.model;

import java.util.List;

public record RecommendationResponse(
        String message,
        List<Course> courses
) {
}
