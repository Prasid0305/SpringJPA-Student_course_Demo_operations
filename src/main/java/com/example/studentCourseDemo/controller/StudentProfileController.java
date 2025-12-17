package com.example.studentCourseDemo.controller;


import com.example.studentCourseDemo.entity.StudentProfile;
import com.example.studentCourseDemo.service.StudentProfileService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {

    private final StudentProfileService service;

    public StudentProfileController(StudentProfileService service) {
        this.service = service;
    }

    @PostMapping("/{studentId}/profile")
    public StudentProfile createProfile(
            @PathVariable Long studentId,
            @Valid @RequestBody StudentProfile profile) {

        return service.createProfile(studentId, profile);
    }
}

