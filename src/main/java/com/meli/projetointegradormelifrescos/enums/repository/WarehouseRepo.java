package com.meli.projetointegradormelifrescos.enums.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.meli.projetointegradormelifrescos.model.Warehouse;

import java.util.Optional;

public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {
    Optional<Warehouse> findWarehouseByCode(Long code);
}