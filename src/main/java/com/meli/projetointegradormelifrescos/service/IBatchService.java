package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseStockDTO;

public interface IBatchService {
    WarehouseStockDTO countStocksByProductId(Long productId);

    BatchDTO productsBySection(Long productId);

    BatchStockResDTO getBatchStockBySection(Integer numberOfDays, Long section);
    BatchStockResDTO getBatchStockByCategory(
        Integer numberOfDays,
        String category,
        String sortBy
    );
}
