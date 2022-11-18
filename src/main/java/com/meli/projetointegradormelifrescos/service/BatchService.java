package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.WarehouseCountDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseStockDTO;
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

        List<WarehouseCountDTO> warehouseCountDTOList = new ArrayList<WarehouseCountDTO>();

        batches.forEach(batch -> warehouseCountDTOList.add(new WarehouseCountDTO(batch.getWarehouse().getCode(), batch.getProductQuantity())));

        WarehouseStockDTO warehouseStockDTO = new WarehouseStockDTO();
        warehouseStockDTO.setProductId(batches.stream().findFirst().get().getProductId());
        warehouseStockDTO.setWarehouses(warehouseCountDTOList);
        return warehouseStockDTO;

    }
}
