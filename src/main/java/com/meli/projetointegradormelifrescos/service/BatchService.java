package com.meli.projetointegradormelifrescos.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.enums.repository.BatchRepo;
import org.springframework.stereotype.Service;

@Service
public class BatchService{
    @Autowired
    BatchRepo batchRepo;

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

}
