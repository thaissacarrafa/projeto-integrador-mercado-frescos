package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.model.Batch;

import java.util.List;

public interface IBatchStockService {
        List<Batch> saveAll(List<Batch> batches);

}
