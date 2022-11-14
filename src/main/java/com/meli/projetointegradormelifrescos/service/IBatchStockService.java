package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.InboundOrder;

import java.util.List;

public interface IBatchStockService {
        List<Batch> batchStockMapper(List<BatchDTO> batches, InboundOrder inboundOrder);
        List<Batch> saveAll(List<Batch> batches);
}
