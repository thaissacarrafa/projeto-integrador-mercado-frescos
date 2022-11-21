package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepo implements JpaRepository<BatchStock, Long> {
}
