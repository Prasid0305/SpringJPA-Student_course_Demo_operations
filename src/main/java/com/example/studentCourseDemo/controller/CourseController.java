package com.example.studentCourseDemo.controller;

import com.example.studentCourseDemo.entity.Course;
import com.example.studentCourseDemo.repository.CourseRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseRepository courseRepository;

    public CourseController(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @PostMapping
    public Course create(@RequestBody Course course) {
        return courseRepository.save(course);
    }
}

