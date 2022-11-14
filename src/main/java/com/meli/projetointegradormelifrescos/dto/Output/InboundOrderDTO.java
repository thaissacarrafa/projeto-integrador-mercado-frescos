package com.meli.projetointegradormelifrescos.dto.Output;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

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

    private List<BatchDTO> batchStock;

    @NotNull
    private Long managerId;

    public InboundOrderDTO(InboundOrder inboundOrder) {
        this.orderNumber = inboundOrder.getId();
        this.orderDate = inboundOrder.getOrderDate();
        this.sectionCode = inboundOrder.getSection().getId();
        this.warehouseCode = inboundOrder.getWarehouse().getId();
        this.managerId = inboundOrder.getManager().getId();
        this.batchStock = inboundOrder.getBatches().stream().map(BatchDTO::new).collect(Collectors.toList());
    }

}
