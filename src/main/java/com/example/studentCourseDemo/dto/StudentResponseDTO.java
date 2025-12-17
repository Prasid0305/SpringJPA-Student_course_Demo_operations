package com.example.studentCourseDemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private StudentProfileDTO profile;
    private List<CourseResponseDTO> courses;
}
