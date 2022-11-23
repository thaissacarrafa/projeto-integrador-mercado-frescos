package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseStockDTO;
import java.time.LocalDate;

public interface IBatchService {
    WarehouseStockDTO countStocksByProductId(Long productId);

    BatchDTO productsBySection(Long productId);

    BatchStockResDTO getBatchStockBySection(Integer numberOfDays, Long section);

    BatchStockResDTO getBatchStockByCategory(
        Integer numberOfDays,
        String category,
        String sortBy
    );

    BatchStockResDTO getExpiredBatches(
        LocalDate dueDate,
        String category,
        String sortBy
    );
}
