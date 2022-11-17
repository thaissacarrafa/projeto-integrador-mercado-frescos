/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.model.PurchaseOrder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseOrderDTO {
    private LocalDate date;
    private Long buyerId;
    private String orderStatus;
    private List<PurchaseProductDTO> products;

    public PurchaseOrderDTO(PurchaseOrder purchaseOrder) {
        this.date = purchaseOrder.getDate();
        this.buyerId = purchaseOrder.getBuyerId();
        this.orderStatus = purchaseOrder.getOrderStatus();
        this.products = purchaseOrder.getProducts().stream().map(PurchaseProductDTO::new).collect(Collectors.toList());
    }
}
