package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.config.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.model.Batch;

import java.util.List;

public interface IBatchStockService {
        List<Batch> saveAll(List<Batch> batches);

        /**
         * retorna lotes armazenados em um setor de um armazem, ordenado por data de validade.
         * @author Amanda Lobo
         * @param days -> Integer
         * @param section -> String
         * @return retorna um BatchDTO
         */
        BatchDTO getAllDueDate(Integer days, String section);
}
