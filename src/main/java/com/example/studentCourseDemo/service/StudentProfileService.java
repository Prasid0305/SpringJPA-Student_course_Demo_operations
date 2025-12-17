package com.example.studentCourseDemo.service;


import com.example.studentCourseDemo.entity.Student;
import com.example.studentCourseDemo.entity.StudentProfile;
import com.example.studentCourseDemo.exception.ResourceNotFoundException;
import com.example.studentCourseDemo.repository.StudentProfileRepository;
import com.example.studentCourseDemo.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentProfileService {

    private final StudentRepository studentRepository;
    private final StudentProfileRepository profileRepository;

    public StudentProfileService(StudentRepository studentRepository,
                                 StudentProfileRepository profileRepository) {
        this.studentRepository = studentRepository;
        this.profileRepository = profileRepository;
    }

    @Transactional
    public StudentProfile createProfile(Long studentId, StudentProfile profile) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Student not found with id " + studentId));

        profile.setStudent(student);

        return profileRepository.save(profile);
    }
}

