package com.meli.projetointegradormelifrescos.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.enums.repository.BatchRepo;

public class BatchService {
    @Autowired
    BatchRepo batchRepo;

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

}
