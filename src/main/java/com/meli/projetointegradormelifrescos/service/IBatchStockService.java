package com.meli.projetointegradormelifrescos.service;
import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;

public interface IBatchStockService {
//  List<BatchStock> saveAll(List<BatchStock> batches);
    BatchStockResDTO getBatchStockBySection(Integer numberOfDays, Long section);
    BatchStockResDTO getBatchStockByCategory(
        Integer numberOfDays,
        String category,
        String sortBy
    );
}
