package com.meli.projetointegradormelifrescos.dto.Input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

import com.meli.projetointegradormelifrescos.model.Batch;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderReqDTO {
    private Long orderNumber;
    @NotBlank(message = "A data do pedido não pode estar vazia.")
    private LocalDate orderDate;

    private Long sectionCode;

    private Long warehouseCode;

    private List<BatchStockReqDTO> batches;
}
