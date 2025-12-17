package com.example.studentCourseDemo.controller;

import com.example.studentCourseDemo.dto.StudentResponseDTO;
import com.example.studentCourseDemo.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<StudentResponseDTO> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponseDTO getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }
}
