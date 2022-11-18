package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;
import com.meli.projetointegradormelifrescos.enums.Category;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public interface IPurchaseProductService {
    HashMap createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);

    HashMap getPurchaseOrder(Long purchaseOrderId);

    HashMap putPurchaseOrder(Long purchaseOrderId);
}
