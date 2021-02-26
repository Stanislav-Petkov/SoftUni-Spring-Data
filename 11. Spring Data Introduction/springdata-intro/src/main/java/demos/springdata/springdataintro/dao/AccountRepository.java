package demos.springdata.springdataintro.dao;

import demos.springdata.springdataintro.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    // says to Spring that findById should return Account
    // Otherwise if there is no manual declaration of the method, the method,
    // will return optional, not returns null or the Account
    Account findById(long id);

    // findAll() returns Iterable<T>, we ca declare it to return List<T> and it will start returning
    // what we have set
}
