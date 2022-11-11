package com.meli.projetointegradormelifrescos.dto.Input;

import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
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

    private LocalDate orderDate;

    private Long sectionCode;

    private Long warehouseCode;

    private List<BatchStockDTO> batches;
}
