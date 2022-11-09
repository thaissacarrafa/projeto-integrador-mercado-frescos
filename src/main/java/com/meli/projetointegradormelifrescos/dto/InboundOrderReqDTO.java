package com.meli.projetointegradormelifrescos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderReqDTO {
    private Long orderNumber;
    @NotBlank(message = "A data do pedido não pode estar vazia.")
    private LocalDate orderDate;

    private Long sectionCode;

    private Long warehouseCode;

    private BatchStockReqDTO[] batchStock;
}
