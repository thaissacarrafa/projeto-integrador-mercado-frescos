package com.meli.projetointegradormelifrescos.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockDTO {
    private Long batchNumber;
    private Long productId;
    @NotNull
    private Float currentTemperature;
    @NotNull
    private int productQuantity;
    @NotNull
    private LocalDateTime manufacturingDate;
    @NotNull
    private LocalDateTime manufacturingTime;
    private Float volume;
    @NotNull
    private LocalDate dueDate;
    private BigDecimal price;
    private boolean alert; // requisito 06 Thaíssa
}




