package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseCountDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseStockDTO;
import com.meli.projetointegradormelifrescos.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.exception.ListIsEmptyException;
import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BatchService implements IBatchService{
    @Autowired
    BatchRepo batchRepo;

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

    @Override
    public WarehouseStockDTO countStocksByProductId(Long productId) {

        List<Batch> batches = batchRepo.findBatchByProductId(productId);

        if (batches.isEmpty()) {
            throw new ListIsEmptyException("Este produto não foi encontrado em nenhum armazém.");
        }

        List<WarehouseCountDTO> warehouseCountDTOList = new ArrayList<WarehouseCountDTO>();

        batches.forEach(batch -> warehouseCountDTOList.add(new WarehouseCountDTO(batch.getWarehouse().getCode(), batch.getProductQuantity())));

        WarehouseStockDTO warehouseStockDTO = new WarehouseStockDTO();
        warehouseStockDTO.setProductId(batches.stream().findFirst().get().getProductId());
        warehouseStockDTO.setWarehouses(warehouseCountDTOList);

        return warehouseStockDTO;

    }

    @Override
    public BatchDTO productsBySection(Long productId) {

        List<Batch> batches = batchRepo.findSectionByProductId(productId);

        List<BatchDTO> batchDTOList = new ArrayList<>();

        batches.forEach(batch -> batchDTOList.add(new BatchDTO(batch)));

        BatchDTO batchDTO = new BatchDTO();
        batchDTO.setProductId(batches.stream().findFirst().get().getProductId());
        batchDTO.setDueDate(batches.stream().findFirst().get().getDueDate());
        batchDTO.setBatchNumber(batches.stream().findFirst().get().getBatchNumber());
        batchDTO.setProductQuantity(batches.stream().findFirst().get().getProductQuantity());

        return batchDTO;

    }
}

