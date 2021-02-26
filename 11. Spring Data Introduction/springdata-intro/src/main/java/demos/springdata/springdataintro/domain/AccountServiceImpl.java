package demos.springdata.springdataintro.domain;

import demos.springdata.springdataintro.dao.AccountRepository;
import demos.springdata.springdataintro.exception.IllegalBankOperationException;
import demos.springdata.springdataintro.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;

@Service("accService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public Account getAccount(long accountId) {
        return accountRepository.findById(accountId);
    }

    @Transactional
    @Override
    public void withdrawMoney(long accountId, BigDecimal amount) {
        // Get the account
        Account account = accountRepository.findById(accountId);
        // if it is less than 0 then account balance is less than the amount
        if(account.getBalance().compareTo(amount) < 0){
            // By default the exception will start a rollback
            throw new IllegalBankOperationException(
                    "Current balance: $" + account.getBalance()
                    + " is not sufficient to withdraw amount: " + amount );
        }

        account.setBalance(account.getBalance().subtract(amount));
        accountRepository.save(account);
    }// The transaction commits when the method execution has finished

    //By default each repository operation is with its own transaction
    @Transactional
    @Override
    public void depositMoney(long accountId, BigDecimal amount) {
        Account account = accountRepository.findById(accountId);
        account.setBalance(account.getBalance().add(amount));
        accountRepository.save(account);
    }

    @Transactional
    @Override
    public void transferMoney(long fromId, long toId, BigDecimal amount) {
        // Both methods should be in the same transaction
        // deposit amount money to toId  account
        depositMoney(toId,amount);
        // withdraw amount money from formId account
        withdrawMoney(fromId,amount);
    }
}
