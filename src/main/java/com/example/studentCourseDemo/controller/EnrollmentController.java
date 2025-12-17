//package com.example.studentCourseDemo.controller;
//
//import com.example.studentCourseDemo.entity.Enrollment;
//import com.example.studentCourseDemo.entity.Student;
//import com.example.studentCourseDemo.service.EnrollmentService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/enrollments")
//public class EnrollmentController {
//
//    private final EnrollmentService enrollmentService;
//
//    public EnrollmentController(EnrollmentService enrollmentService) {
//        this.enrollmentService = enrollmentService;
//    }
//
//
//    @PostMapping("/students/{studentId}/courses/{courseId}")
//    public Enrollment enrollStudentInCourse(@PathVariable Long studentId,
//                                            @PathVariable Long courseId) {
//        return enrollmentService.enrollStudentInCourse(studentId, courseId);
//    }
//
//
//    @GetMapping("/students/morethan/{count}/courses")
//    public List<Student> getStudentsWithMoreThanNCourses(@PathVariable long count) {
//        return enrollmentService.getStudentsWithMoreThanNCourses(count);
//    }
//}
package com.example.studentCourseDemo.controller;

import com.example.studentCourseDemo.dto.StudentResponseDTO;
import com.example.studentCourseDemo.entity.Enrollment;
import com.example.studentCourseDemo.service.EnrollmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    // -------------------- ENROLL --------------------

    @PostMapping("/students/{studentId}/courses/{courseId}")
    public Enrollment enrollStudentInCourse(@PathVariable Long studentId,
                                            @PathVariable Long courseId) {
        return enrollmentService.enrollStudentInCourse(studentId, courseId);
    }

    // -------------------- QUERY --------------------

    @GetMapping("/students/morethan/{count}/courses")
    public List<StudentResponseDTO> getStudentsWithMoreThanNCourses(
            @PathVariable long count) {

        return enrollmentService.getStudentsWithMoreThanNCourses(count);
    }
}
