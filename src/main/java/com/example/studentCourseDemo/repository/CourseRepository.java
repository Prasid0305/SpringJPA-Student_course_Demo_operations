package com.example.studentCourseDemo.repository;



import com.example.studentCourseDemo.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}

