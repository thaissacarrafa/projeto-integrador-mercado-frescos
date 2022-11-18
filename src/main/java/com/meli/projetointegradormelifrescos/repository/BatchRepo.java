package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface BatchRepo extends JpaRepository<Batch, Long> {
    Optional<Batch> findBatchById(Long batchNumber);

}
