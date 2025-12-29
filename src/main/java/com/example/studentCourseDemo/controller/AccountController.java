package com.example.studentCourseDemo.controller;

import com.example.studentCourseDemo.dto.AccountCreateRequest;
import com.example.studentCourseDemo.dto.TransferRequest;
import com.example.studentCourseDemo.entity.Account;
import com.example.studentCourseDemo.service.AccountService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping
    public Account createAccount(@RequestBody AccountCreateRequest request) {
        return accountService.createAccount(
                request.getHolderName(),
                request.getBalance()
        );
    }


    @PostMapping("/transfer")
    public void transfer(@RequestBody TransferRequest request) {
        accountService.transfer(
                request.getFromId(),
                request.getToId(),
                request.getAmount()
        );
    }
}
