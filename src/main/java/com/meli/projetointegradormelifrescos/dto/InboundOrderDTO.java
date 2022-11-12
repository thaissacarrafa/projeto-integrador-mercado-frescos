package com.meli.projetointegradormelifrescos.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InboundOrderDTO {
    private Long orderNumber;
    @NotNull
    private LocalDate orderDate;

    private Long sectionCode;

    private Long warehouseCode;

    private List<BatchStockDTO> batchStock;

    @NotNull
    private Long managerId;
}