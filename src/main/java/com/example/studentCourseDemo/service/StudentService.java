package com.example.studentCourseDemo.service;

import com.example.studentCourseDemo.dto.CourseResponseDTO;
import com.example.studentCourseDemo.dto.StudentProfileDTO;
import com.example.studentCourseDemo.dto.StudentRequestDTO;
import com.example.studentCourseDemo.dto.StudentResponseDTO;
import com.example.studentCourseDemo.entity.Enrollment;
import com.example.studentCourseDemo.entity.Student;
import com.example.studentCourseDemo.exception.ResourceNotFoundException;
import com.example.studentCourseDemo.repository.StudentRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    // ===================== CREATE =====================

    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public StudentResponseDTO createStudent(StudentRequestDTO dto) {

        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());

        Student savedStudent = studentRepository.save(student);

        StudentResponseDTO response = new StudentResponseDTO();
        response.setId(savedStudent.getId());
        response.setName(savedStudent.getName());
        response.setEmail(savedStudent.getEmail());
        response.setProfile(null);
        response.setCourses(List.of());

        return response;
    }

    // ===================== READ =====================

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Transactional(readOnly = true)
    public StudentResponseDTO getStudentById(Long id) {

        Student student = studentRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id: " + id));

        return mapToDTO(student);
    }

    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    @Transactional(readOnly = true)
    public List<StudentResponseDTO> getAllStudents() {

        return studentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    // ===================== MAPPER =====================

    private StudentResponseDTO mapToDTO(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());

        // Profile
        if (student.getProfile() != null) {
            StudentProfileDTO profileDTO = new StudentProfileDTO();
            profileDTO.setAddress(student.getProfile().getAddress());
            profileDTO.setPhone(student.getProfile().getPhone());
            dto.setProfile(profileDTO);
        }

        // Courses
        List<CourseResponseDTO> courses = student.getEnrollments()
                .stream()
                .map(Enrollment::getCourse)
                .map(course -> {
                    CourseResponseDTO c = new CourseResponseDTO();
                    c.setId(course.getId());
                    c.setTitle(course.getTitle());
                    return c;
                })
                .toList();

        dto.setCourses(courses);

        return dto;
    }
}
