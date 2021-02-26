package com.example.demo.repositories;

import com.example.demo.models.entitites.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    //Ex_6_Query_3
    //Get all suppliers that do not import parts from abroad. Get their id,
    // name and the number of parts they can offer to supply.
    @Query(value = "SELECT * " +
            "FROM suppliers as s\n" +
            "inner JOIN parts p on s.id = p.supplier_id\n" +
            "WHERE is_importer = false\n" +
            "GROUP BY s.id;",nativeQuery = true)
    List<Supplier> findAllThatDoNotImport();
}
