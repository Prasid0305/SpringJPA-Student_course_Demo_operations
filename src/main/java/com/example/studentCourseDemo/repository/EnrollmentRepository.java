package com.example.studentCourseDemo.repository;



import com.example.studentCourseDemo.entity.Enrollment;
import com.example.studentCourseDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);

    @Query("""
    SELECT s
    FROM Student s
    WHERE s.id IN (
        SELECT e.student.id
        FROM Enrollment e
        GROUP BY e.student.id
        HAVING COUNT(e.course.id) > :count
    )
""")
    List<Student> findStudentsEnrolledInMoreThan(@Param("count") long count);

}

