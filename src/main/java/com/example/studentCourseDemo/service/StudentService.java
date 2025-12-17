package com.example.studentCourseDemo.service;

import com.example.studentCourseDemo.dto.CourseResponseDTO;
import com.example.studentCourseDemo.dto.StudentProfileDTO;
import com.example.studentCourseDemo.dto.StudentResponseDTO;
import com.example.studentCourseDemo.entity.Enrollment;
import com.example.studentCourseDemo.entity.Student;
import com.example.studentCourseDemo.exception.ResourceNotFoundException;
import com.example.studentCourseDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public StudentResponseDTO getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found"));

        return mapToDTO(student);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    private StudentResponseDTO mapToDTO(Student student) {
        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());

        // Profile mapping
        if (student.getProfile() != null) {
            StudentProfileDTO profileDTO = new StudentProfileDTO();
            profileDTO.setAddress(student.getProfile().getAddress());
            profileDTO.setPhone(student.getProfile().getPhone());
            dto.setProfile(profileDTO);
        }

        // Courses mapping via Enrollment
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
