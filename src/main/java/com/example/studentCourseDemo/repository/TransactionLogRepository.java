package com.example.studentCourseDemo.repository;

import com.example.studentCourseDemo.entity.TransactionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionLogRepository extends JpaRepository<TransactionLog, Long> {
}

