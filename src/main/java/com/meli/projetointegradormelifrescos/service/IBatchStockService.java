package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.BatchNearDueDateDTO;
import com.meli.projetointegradormelifrescos.model.Batch;

import java.time.LocalDate;
import java.util.List;

public interface IBatchStockService {

        public List<BatchNearDueDateDTO> getBatchStockBySection(Integer numberOfDays, Long section);
        List<Batch> saveAll(List<Batch> batches);

}
