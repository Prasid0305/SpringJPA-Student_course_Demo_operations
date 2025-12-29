package com.example.studentCourseDemo.repository;

import com.example.studentCourseDemo.entity.Student;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @EntityGraph(attributePaths = {
            "profile",
            "enrollments",
            "enrollments.course"
    })
    List<Student> findAll();

    @EntityGraph(attributePaths = {
            "profile",
            "enrollments",
            "enrollments.course"
    })
    Optional<Student> findById(Long id);
}

