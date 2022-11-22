package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;

import java.util.HashMap;


public interface IPurchaseProductService {
    HashMap createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    HashMap getPurchaseOrder(Long purchaseOrderId);

    HashMap putPurchaseOrder(Long purchaseOrderId);
}
