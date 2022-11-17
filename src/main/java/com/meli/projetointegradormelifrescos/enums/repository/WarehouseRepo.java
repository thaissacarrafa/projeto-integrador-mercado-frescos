package com.meli.projetointegradormelifrescos.enums.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import com.meli.projetointegradormelifrescos.model.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface WarehouseRepo extends JpaRepository<Warehouse,Long> {
    Optional<Warehouse> findWarehouseByCode(Long code);
}