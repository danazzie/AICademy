package com.aicademy.model;

import java.util.List;

public record Course(
        long id,
        String title,
        String subtitle,
        String category,
        String provider,
        String level,
        String instructor,
        double rating,
        int students,
        int hours,
        String price,
        String url,
        List<String> tags,
        List<String> roles,
        List<String> goals,
        String highlight
) {
}
