package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BatchRepo extends JpaRepository<BatchStock, Long> {

    List<BatchStock> findAllByProductId(Long productId);

    List<BatchStock> findAllByDueDateBetween(LocalDate today, LocalDate dueDate);
}
