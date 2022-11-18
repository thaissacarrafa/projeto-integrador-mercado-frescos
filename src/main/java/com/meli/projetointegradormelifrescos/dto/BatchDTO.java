package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Batch;

import com.meli.projetointegradormelifrescos.model.Warehouse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDTO {
    private Long batchNumber;

    private Long productId;

    @NotNull
    private Float currentTemperature;

    @NotNull
    private int productQuantity;

    @NotNull
    private LocalDate manufacturingDate;

    @NotNull
    private LocalDateTime manufacturingTime;

    private Float volume;

    @NotNull
    private LocalDate dueDate;

    private BigDecimal price;

    private WarehouseDTO warehouse;


    public BatchDTO(Batch batch) {
        this.batchNumber = batch.getBatchNumber();
        this.productId = batch.getProductId();
        this.productQuantity = batch.getInitialQuantity();
        this.currentTemperature = batch.getCurrentTemperature();
        this.manufacturingDate = batch.getManufacturingDate();
        this.manufacturingTime = batch.getManufacturingTime();
        this.volume = batch.getVolume();
        this.dueDate = batch.getDueDate();
        this.price = batch.getPrice();
    }

    public static Batch entityToDTO(BatchDTO batchDTO) {
        Batch batch = new Batch();

        batch.setBatchNumber(batchDTO.getBatchNumber());
        batch.setProductId(batchDTO.getProductId());
        batch.setInitialQuantity(batchDTO.getProductQuantity());
        batch.setCurrentTemperature(batchDTO.getCurrentTemperature());
        batch.setManufacturingDate(batchDTO.getManufacturingDate());
        batch.setManufacturingTime(batchDTO.getManufacturingTime());
        batch.setVolume(batchDTO.getVolume());
        batch.setDueDate(batchDTO.getDueDate());
        batch.setPrice(batchDTO.getPrice());

        return batch;

    }
}
