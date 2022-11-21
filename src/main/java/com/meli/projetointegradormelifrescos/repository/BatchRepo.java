package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.BatchStock;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo extends JpaRepository<BatchStock, Long> {
    List<BatchStock> findAllByProductId(Long productId);

    List<BatchStock> findAllByDueDateBetween(
        LocalDate today,
        LocalDate dueDate
    );
}
