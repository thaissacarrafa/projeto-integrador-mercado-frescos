package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.model.Batch;

import java.util.List;

public interface IBatchStockService {
        List<Batch> saveAll(List<Batch> batches);
}
