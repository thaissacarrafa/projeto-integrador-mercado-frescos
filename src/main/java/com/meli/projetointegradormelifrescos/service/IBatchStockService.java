package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.model.BatchStock;
import com.meli.projetointegradormelifrescos.model.InboundOrder;

import java.util.List;

public interface IBatchStockService {
        List<BatchStock> batchStockMapper(List<BatchStockDTO> batches, InboundOrder inboundOrder);
        List<BatchStock> saveAll(List<BatchStock> batches);
}
