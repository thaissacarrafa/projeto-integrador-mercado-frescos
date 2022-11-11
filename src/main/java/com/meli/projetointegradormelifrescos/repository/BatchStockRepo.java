package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.model.BatchStock;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BatchStockRepo extends JpaRepository<BatchStock, Long> {

}
