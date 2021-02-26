package com.example.demo.repositories;

import com.example.demo.models.entitites.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // Ex_6_Query_1
    // Get all customers, ordered by their birthdate in ascending
    // order. If two customers are born on the same date, first print those,
    // who are not young drivers (e.g. print experienced drivers first).
    @Query(value = "SELECT *\n" +
            "FROM customers\n" +
            "ORDER BY birth_date ASC ,\n" +
            "CASE WHEN birth_date = birth_date THEN young_driver = TRUE END ;",
    nativeQuery = true)
    List<Customer> findAllByOrderByBirthDateAsc();

    //// Ex_6_Query_5
    @Query(value = "SELECT  t.fullName as fullName,\n" +
            "       boughtCars as boughtCars,\n" +
            "       sum(p.price) as spentMoney\n" +
            "FROM (\n" +
            "         SELECT c.id,c.name as fullName, count(distinct s.car_id) as boughtCars\n" +
            "         FROM customers as c\n" +
            "         INNER JOIN sales s on c.id = s.customer_id\n" +
            "         GROUP BY s.customer_id\n" +
            "         ) as t\n" +
            "inner join cars c2 on t.id = c2.id\n" +
            "inner join parts_cars pc on c2.id = pc.car_id\n" +
            "inner join parts p on pc.part_id = p.id\n" +
            "GROUP BY c2.id " +
            "ORDER BY spentMoney DESC, boughtCars DESC ",
    nativeQuery = true)
    List<Object[]> findAllCustomersWithNumberOfBoughtCarsAndSpentMoney();

}
