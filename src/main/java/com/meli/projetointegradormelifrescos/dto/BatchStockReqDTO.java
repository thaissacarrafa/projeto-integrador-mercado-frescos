package com.meli.projetointegradormelifrescos.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchStockReqDTO {
    private Long batchNumber;

    private Long productId;

    @NotBlank(message = "A temperatura atual não pode estar vazia")
    private Float currentTemperature;

    private int productQuantity;

    private LocalDate manufacturingDate;

    private LocalDateTime manufacturingTime;

    private Float volume;

    private LocalDate dueDate;

    private BigDecimal price;
}
