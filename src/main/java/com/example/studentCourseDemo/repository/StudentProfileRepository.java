package com.example.studentCourseDemo.repository;


import com.example.studentCourseDemo.entity.StudentProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentProfileRepository extends JpaRepository<StudentProfile, Long> {
}