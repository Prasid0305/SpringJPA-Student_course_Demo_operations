package com.example.studentCourseDemo.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountCreateRequest {
    private String holderName;
    private double balance;
}

