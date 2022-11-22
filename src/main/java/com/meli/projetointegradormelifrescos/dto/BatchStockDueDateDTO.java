package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Batch;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public BatchStockDueDateDTO(Batch batch) {
        this.batchNumber = batch.getBatchNumber();
        this.productId = batch.getProductId();
        this.productTypeId = batch.getSection().getName();
        this.dueDate = batch.getDueDate();
        this.quantity = batch.getProductQuantity();
    }
}
