package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.BatchStock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BatchStockDueDateDTO {
    private Long batchNumber;
    private Long productId;
    private String productTypeId;
    private LocalDate dueDate;
    private Integer quantity;

    public BatchStockDueDateDTO(BatchStock batchStock) {
        this.batchNumber = batchStock.getBatchNumber();
        this.productId = batchStock.getProductId();
        this.productTypeId = batchStock.getSection().getName();
        this.dueDate = batchStock.getDueDate();
        this.quantity = batchStock.getProductQuantity();
    }
}
