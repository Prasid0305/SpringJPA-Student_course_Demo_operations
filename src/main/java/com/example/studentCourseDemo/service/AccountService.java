package com.example.studentCourseDemo.service;

import com.example.studentCourseDemo.entity.Account;
import com.example.studentCourseDemo.exception.InsufficientBalanceException;
import com.example.studentCourseDemo.exception.ResourceNotFoundException;
import com.example.studentCourseDemo.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final TransactionLogService logService;

    public AccountService(AccountRepository accountRepository,
                          TransactionLogService logService) {
        this.accountRepository = accountRepository;
        this.logService = logService;
    }

    public Account createAccount(String holderName, double balance) {
        Account account = new Account();
        account.setHolderName(holderName);
        account.setBalance(balance);
        return accountRepository.save(account);
    }

    @Transactional
    public void transfer(Long fromId, Long toId, double amount) {

        try {
            Account from = accountRepository.findById(fromId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("From account not found: " + fromId));

            Account to = accountRepository.findById(toId)
                    .orElseThrow(() ->
                            new ResourceNotFoundException("To account not found: " + toId));

            if (from.getBalance() < amount) {
                throw new InsufficientBalanceException("Insufficient balance");
            }

            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);

            accountRepository.save(from);
            accountRepository.save(to);


            logService.logTransfer(fromId, toId, amount, "SUCCESS");

        } catch (RuntimeException ex) {


            logService.logTransfer(fromId, toId, amount, "FAILED");
            throw ex;
        }
    }
}
