/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.model.Section;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
        this.batchStock =
            inboundOrder
                .getBatches()
                .stream()
                .map(BatchDTO::new)
                .collect(Collectors.toList());
    }
}
