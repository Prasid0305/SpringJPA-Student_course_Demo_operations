package com.example.studentCourseDemo.repository;

import com.example.studentCourseDemo.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
