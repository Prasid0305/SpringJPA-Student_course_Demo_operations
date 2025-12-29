package com.example.studentCourseDemo.service;

import com.example.studentCourseDemo.entity.TransactionLog;
import com.example.studentCourseDemo.repository.TransactionLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class TransactionLogService {

    private final TransactionLogRepository logRepository;

    public TransactionLogService(TransactionLogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void logTransfer(Long fromId, Long toId, double amount, String status) {

        TransactionLog log = new TransactionLog();
        log.setFromAccountId(fromId);
        log.setToAccountId(toId);
        log.setAmount(amount);
        log.setStatus(status);
        log.setCreatedAt(LocalDateTime.now());

        logRepository.save(log);
    }
}
