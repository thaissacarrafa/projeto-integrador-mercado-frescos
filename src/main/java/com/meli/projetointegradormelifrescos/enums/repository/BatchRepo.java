package com.meli.projetointegradormelifrescos.enums.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BatchRepo extends JpaRepository<Batch, Long> {
    List<Batch> findBatchByProductId(Long productId);
    List<Batch> findSectionByProductId(Long productId);

}
