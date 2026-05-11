package com.aicademy.controller;

import com.aicademy.model.Course;
import com.aicademy.model.RecommendationResponse;
import com.aicademy.model.UserProfile;
import com.aicademy.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public List<Course> getCourses() {
        return courseService.getAllCourses();
    }

    @PostMapping("/recommendations")
    public RecommendationResponse recommend(@Valid @RequestBody UserProfile profile) {
        return courseService.recommend(profile);
    }
}
