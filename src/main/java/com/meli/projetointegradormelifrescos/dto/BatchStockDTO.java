package com.meli.projetointegradormelifrescos.dto;

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
    @NotBlank(message = "A temperatura atual não pode estar vazia")
    private Float currentTemperature;
    @NotBlank(message = "A quantidade não pode estar vazia")
    private int productQuantity;
    @NotBlank(message = "A data de fabricação não pode estar vazia")
    private LocalDateTime manufacturingDate;
    @NotBlank(message = "A hora de fabricação não pode estar vazia")
    private LocalDateTime manufacturingTime;
    private Float volume;
    @NotBlank(message = "A data de validade não pode estar vazia")
    private LocalDate dueDate;
    private BigDecimal price;
}




