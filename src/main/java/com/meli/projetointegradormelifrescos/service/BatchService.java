package com.meli.projetointegradormelifrescos.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;

public class BatchService {
    @Autowired
    BatchRepo batchRepo;

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

}