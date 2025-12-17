//package com.example.studentCourseDemo.service;
//
//import com.example.studentCourseDemo.entity.Course;
//import com.example.studentCourseDemo.entity.Enrollment;
//import com.example.studentCourseDemo.entity.Student;
//import com.example.studentCourseDemo.exception.ResourceNotFoundException;
//import com.example.studentCourseDemo.repository.CourseRepository;
//import com.example.studentCourseDemo.repository.EnrollmentRepository;
//import com.example.studentCourseDemo.repository.StudentRepository;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Service
//public class EnrollmentService {
//
//    private final EnrollmentRepository enrollmentRepository;
//    private final StudentRepository studentRepository;
//    private final CourseRepository courseRepository;
//
//    public EnrollmentService(EnrollmentRepository enrollmentRepository,
//                             StudentRepository studentRepository,
//                             CourseRepository courseRepository) {
//        this.enrollmentRepository = enrollmentRepository;
//        this.studentRepository = studentRepository;
//        this.courseRepository = courseRepository;
//    }
//
//
//    @Transactional
//    public Enrollment enrollStudentInCourse(Long studentId, Long courseId) {
//
//        Student student = studentRepository.findById(studentId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Student not found with id " + studentId));
//
//        Course course = courseRepository.findById(courseId)
//                .orElseThrow(() ->
//                        new ResourceNotFoundException("Course not found with id " + courseId));
//
//        Enrollment enrollment = new Enrollment();
//        enrollment.setStudent(student);
//        enrollment.setCourse(course);
//
//        return enrollmentRepository.save(enrollment);
//    }
//
//
//    public List<Student> getStudentsWithMoreThanNCourses(long count) {
//        return enrollmentRepository.findStudentsEnrolledInMoreThan(count);
//    }
//}

package com.example.studentCourseDemo.service;

import com.example.studentCourseDemo.dto.CourseResponseDTO;
import com.example.studentCourseDemo.dto.StudentProfileDTO;
import com.example.studentCourseDemo.dto.StudentResponseDTO;
import com.example.studentCourseDemo.entity.Course;
import com.example.studentCourseDemo.entity.Enrollment;
import com.example.studentCourseDemo.entity.Student;
import com.example.studentCourseDemo.exception.ResourceNotFoundException;
import com.example.studentCourseDemo.repository.CourseRepository;
import com.example.studentCourseDemo.repository.EnrollmentRepository;
import com.example.studentCourseDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository,
                             StudentRepository studentRepository,
                             CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    // -------------------- ENROLL --------------------

    @Transactional
    public Enrollment enrollStudentInCourse(Long studentId, Long courseId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student not found with id " + studentId));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Course not found with id " + courseId));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setCourse(course);

        return enrollmentRepository.save(enrollment);
    }

    // -------------------- QUERY + DTO --------------------

    public List<StudentResponseDTO> getStudentsWithMoreThanNCourses(long count) {

        List<Student> students =
                enrollmentRepository.findStudentsEnrolledInMoreThan(count);

        return students.stream()
                .map(this::mapToDTO)
                .toList();
    }

    // -------------------- MAPPER --------------------

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

        // Courses (via enrollments)
        List<CourseResponseDTO> courses =
                student.getEnrollments().stream()
                        .map(enrollment -> {
                            CourseResponseDTO c = new CourseResponseDTO();
                            c.setId(enrollment.getCourse().getId());
                            c.setTitle(enrollment.getCourse().getTitle());
                            return c;
                        })
                        .toList();

        dto.setCourses(courses);

        return dto;
    }
}

