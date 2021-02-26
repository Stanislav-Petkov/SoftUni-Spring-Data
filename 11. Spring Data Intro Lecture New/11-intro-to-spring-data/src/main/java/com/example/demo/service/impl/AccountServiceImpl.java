package com.example.demo.service.impl;

import com.example.demo.dao.AccountRepository;
import com.example.demo.entity.Account;
import com.example.demo.entity.User;
import com.example.demo.exception.InvalidAccountOperationException;
import com.example.demo.exception.NonExistingEntityException;
import com.example.demo.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepo;

    @Autowired
    public void setAccountRepo(AccountRepository accountRepo){
        this.accountRepo = accountRepo;
    }

    @Override
    public Account createUserAccount(User user, Account account) {
        // Create new account with null id
        account.setId(null);
        account.setUser(user);
        user.getAccounts().add(account);
        return accountRepo.save(account);
    }

    // All runtime exceptions by default rollback a transaction
    @Override
    public void withdrawMoney(BigDecimal amount, Long accountId) {
       Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(
                   String.format("Entity with ID:%s does not exits",accountId)
                ));
       if(account.getBalance().compareTo(amount) < 0){
           throw new InvalidAccountOperationException(
                   String.format("Error withdrawing amount: %s. Insufficient amount: %s in account ID:%s",
                            amount, account.getBalance(), accountId)
           );
       }
       account.setBalance(account.getBalance().subtract(amount));
    }//commit transaction - @Transactional

    @Override
    public void depositMoney(BigDecimal amount, Long accountId) {
        Account account = accountRepo.findById(accountId).orElseThrow(() ->
                new NonExistingEntityException(
                        String.format("Entity with ID:%s does not exit,.",
                                accountId)
                ));
        account.setBalance(account.getBalance().add(amount));
    }

    @Override
    public void transferMoney(BigDecimal amount, Long fromAccountId, Long toAccountId) {
        // The two methods are in a common transaction
        depositMoney(amount, toAccountId);
        withdrawMoney(amount, fromAccountId);
    }// Commit transaction

    @Override
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }
}





















