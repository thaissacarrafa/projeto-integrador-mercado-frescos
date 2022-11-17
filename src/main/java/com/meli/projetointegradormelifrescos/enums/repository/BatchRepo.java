package com.meli.projetointegradormelifrescos.enums.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface BatchRepo extends JpaRepository<Batch, Long> {

    @Query(value = "select b from Batch b where b.productQuantity like :productQuantity order by b.productQuantity asc", nativeQuery = true)
    List<Batch> findProductQuantityByBatch(@Param("productQuantity") int productQuantity);

    @Query(value = "select b from Batch b where b.dueDate like :dueDate order by b.dueDate asc", nativeQuery = true)
    List<Batch> findDueDateByBatch(@Param("dueDate") LocalDate dueDate);
}
