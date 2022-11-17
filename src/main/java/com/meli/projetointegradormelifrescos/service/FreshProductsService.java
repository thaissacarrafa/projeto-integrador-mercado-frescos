package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseProductDTO;
import com.meli.projetointegradormelifrescos.enums.Category;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.model.PurchaseOrder;
import com.meli.projetointegradormelifrescos.model.PurchaseProduct;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.repository.PurchaseOrderRepo;
import com.meli.projetointegradormelifrescos.repository.PurchaseProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FreshProductsService implements IFreshProductsService {
    @AutoConfigureOrder
    private PurchaseOrder purchaseOrder;

    @Autowired
    private PurchaseOrderRepo purchaseOrderRepo;

    @Autowired
    private PurchaseProductRepo purchaseProductRepo;

    @Override
    public List<AnnoucementDTO> getAllProductsAnnoucement() {
        return null;
    }

    @Override
    public List<AnnoucementDTO> getAllProductsByCategory(Optional<Category> category) {
        return null;
    }

    @Override
    public Integer createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {

        PurchaseOrder purchaseOrderEntity = new PurchaseOrder();

        purchaseOrderEntity.setDate(purchaseOrderDTO.getDate());
        purchaseOrderEntity.setBuyerId(purchaseOrderDTO.getBuyerId());
        purchaseOrderEntity.setOrderStatus(purchaseOrderDTO.getOrderStatus());

        purchaseOrderRepo.save(purchaseOrderEntity);
        purchaseOrderDTO.getProducts().forEach(b -> savePurchaseProduct(b, purchaseOrderEntity));

        return new Integer(243);
    };

    private PurchaseProduct savePurchaseProduct(PurchaseProductDTO dto, PurchaseOrder purchaseOrder) {
        PurchaseProduct purchaseProduct = new PurchaseProduct();
        purchaseProduct.setQuantity(dto.getQuantity());
        purchaseProduct.setProductId(dto.getProductId());
        purchaseProduct.setPurchaseOrder(purchaseOrder);
        purchaseProductRepo.save(purchaseProduct);
        return purchaseProduct;
    }
}
