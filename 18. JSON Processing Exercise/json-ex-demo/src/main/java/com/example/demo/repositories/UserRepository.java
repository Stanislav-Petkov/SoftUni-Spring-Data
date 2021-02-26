package com.example.demo.repositories;

import com.example.demo.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long > {
    //Ex_3_Query_2_Successfully_Sold_Products
//    @Query(value = " SELECT * " +
//            " FROM users AS u" +
//            " INNER JOIN products AS p ON u.id = p.seller_id " +
//            " WHERE p.buyer_id IS NOT NULL " +
//            " GROUP BY seller_id " +
//            " HAVING count(*) > 1 " +
//            " ORDER BY u.last_name ,u.first_name ",
//            nativeQuery = true)
    @Query(value = "SELECT DISTINCT u FROM User AS u INNER JOIN Product AS p " +
            "ON u.id = p.seller.id " +
            "WHERE size(u.sold) > 1 AND p.buyer IS NOT NULL " +
            "ORDER BY u.lastName, u.firstName ")
    List<User> findAllUsersWithAtLeastOneSoldProduct();

    //Ex_3_Query_4
    @Query(value = "SELECT DISTINCT u " +
            " FROM User AS u " +
            " WHERE size(u.sold) > 1 " +
            " ORDER BY u.sold.size DESC, u.lastName ASC ")
    List<User> findAllUsersWithMoreThan1SoldProducts();
}
