package com.meli.projetointegradormelifrescos.repository.test;

import com.meli.projetointegradormelifrescos.dto.WarehouseDTO;
import com.meli.projetointegradormelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface IBatchRepo extends JpaRepository<Batch, Long> {
    @Query(value = "select b.productQuantity from Batch b where b.productId = :id")
    Integer testeDeQuery(Long id);
}
