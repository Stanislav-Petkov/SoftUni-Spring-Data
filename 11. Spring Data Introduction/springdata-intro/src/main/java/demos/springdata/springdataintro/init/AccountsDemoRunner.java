package demos.springdata.springdataintro.init;

import demos.springdata.springdataintro.domain.AccountService;
import demos.springdata.springdataintro.domain.UserService;
import demos.springdata.springdataintro.exception.IllegalBankOperationException;
import demos.springdata.springdataintro.model.Account;
import demos.springdata.springdataintro.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

//Slf4j injects a logger

@Component
@Slf4j
public class AccountsDemoRunner implements ApplicationRunner {

    // inject the repo with a service
    @Autowired
    private UserService userService;

    @Autowired
    private AccountService accountService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user1 = new User("Ivan Petrov", 35);
        User user2 = new User("Stamat Dimitrov", 49);

        Account account1 = new Account(new BigDecimal(5200), user1);
        // add the account to the user
        user1.getAccounts().add(account1);

        Account account2 = new Account(new BigDecimal(35000), user2);
        user2.getAccounts().add(account2);

        // persist the User and he will cascade persist his accounts
        userService.registerUser(user1);
        userService.registerUser(user2);

        // model the services with the domain terms
        // registerUser method corresponds to the domain

        //log how much money did the account have before
        log.info("!!! Initial balance: {}",
                accountService.getAccount(account1.getId()));

        //make a business operation withdraw money
        accountService.withdrawMoney(account1.getId(), new BigDecimal(500));
        log.info("!!! Balance after withdraw $500: {}",
                accountService.getAccount(account1.getId()));

        accountService.depositMoney(account1.getId(), new BigDecimal(200));
        log.info("!!! Balance after deposit $200: {}",
                accountService.getAccount(account1.getId()));

        try{
            accountService.transferMoney(account1.getId(),account2.getId(),new BigDecimal(2000));
        }catch (IllegalBankOperationException e){
            log.error(e.getMessage());
        }
        log.info("!!! Balance FROM after transfer $2000: {}",
                accountService.getAccount(account1.getId()));
        log.info("!!! Balance TO after transfer $2000: {}",
                accountService.getAccount(account2.getId()));

        // Try with not enough money available and throw an error
//        try{
//            accountService.transferMoney(account1.getId(),account2.getId(),new BigDecimal(7000));
//        }catch (IllegalBankOperationException e){
//            log.error(e.getMessage());
//        }
//
//        log.info("!!! Balance FROM after transfer $7000: {}",
//                accountService.getAccount(account1.getId()));
//        log.info("!!! Balance TO after transfer $7000: {}",
//                accountService.getAccount(account2.getId()));
    }
}
