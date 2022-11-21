package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.BatchStock;
import java.util.List;
import java.util.stream.Collectors;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BatchStockResDTO {

    List<BatchStockDueDateDTO> batchStock;

    public BatchStockResDTO(List<BatchStock> batchStock) {
        this.batchStock =
            batchStock
                .stream()
                .map(BatchStockDueDateDTO::new)
                .collect(Collectors.toList());
    }
}
