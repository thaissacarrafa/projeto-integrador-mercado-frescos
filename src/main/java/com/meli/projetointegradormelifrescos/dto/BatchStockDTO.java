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
    private Long productId;
    private Long batchNumber;
    @NotBlank(message = "A temperatura atual não pode estar vazia")
    private Float currentTemperature;
    @NotBlank(message = "A temperatura mínima não pode estar vazia")
    private Float minimumTemperature;
    @NotBlank(message = "A quantidade inicial não pode estar vazia")
    private Integer initialQuantity;
    @NotBlank(message = "A quantidade atual não pode estar vazia")
    private Integer currentQuantity;
    @NotBlank(message = "A data de fabricação não pode estar vazia")
    private LocalDateTime manufacturingDateTime;
    @NotBlank(message = "A data de validade não pode estar vazia")
    private LocalDate dueDate;
    private BigDecimal price;
}



