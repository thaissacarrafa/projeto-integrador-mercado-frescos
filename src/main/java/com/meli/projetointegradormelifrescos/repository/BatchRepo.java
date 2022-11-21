package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProductId(Long productId);

    List<Batch> findAllByDueDateBetween(
        LocalDate today,
        LocalDate dueDate
    );
}
