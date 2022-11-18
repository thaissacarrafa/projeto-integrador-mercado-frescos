package com.meli.projetointegradormelifrescos.service;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseProductDTO;
import com.meli.projetointegradormelifrescos.enums.Category;

import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.*;
import com.meli.projetointegradormelifrescos.repository.AnnoucementRepo;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.repository.PurchaseOrderRepo;
import com.meli.projetointegradormelifrescos.repository.PurchaseProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
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

    @Autowired
    private AnnoucementRepo annoucementRepo;

    @Override
    public List<AnnoucementDTO> getAllProductsAnnoucement() {
        return null;
    }

    @Override
    public List<AnnoucementDTO> getAllProductsByCategory(Optional<Category> category) {
        return null;
    }

    @Override
    public HashMap createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO) {
        List<Long> announcementsNotFound = new ArrayList<>();
        List<PurchaseProduct> products = new ArrayList<>();
        double totalPrice = 0;

        PurchaseOrder purchaseOrderEntity = new PurchaseOrder();
        purchaseOrderEntity.setDate(purchaseOrderDTO.getDate());
        purchaseOrderEntity.setBuyerId(purchaseOrderDTO.getBuyerId());
        purchaseOrderEntity.setOrderStatus(purchaseOrderDTO.getOrderStatus());

        purchaseOrderDTO.getProducts().forEach(b -> {
            PurchaseProduct purchaseProduct = convertPurchaseProduct(b, purchaseOrderEntity);

            if (purchaseProduct != null) {
                products.add(purchaseProduct);
            } else {
                announcementsNotFound.add(b.getProductId());
            }
        });

        if (announcementsNotFound.size() > 0) {
            HashMap<String, List> response = new HashMap<>();
            response.put("announcementsNotFound", announcementsNotFound);
            return response;
        } else {
            purchaseOrderRepo.save(purchaseOrderEntity);
            for (PurchaseProduct b : products) {
                totalPrice += (b.getAnnouncement().getPrice().multiply(BigDecimal.valueOf(b.getQuantity()))).doubleValue();
                purchaseProductRepo.save(b);
            }

            HashMap<String, String> response = new HashMap<>();
            response.put("totalPrice", String.valueOf(totalPrice));

            return response;
        }
    };

    private PurchaseProduct convertPurchaseProduct(PurchaseProductDTO dto, PurchaseOrder purchaseOrder) {
        PurchaseProduct purchaseProduct = new PurchaseProduct();

        Optional<Announcement> announcement = annoucementRepo.findById(dto.getProductId());
        if (announcement.isEmpty()) {
            return null;
        }
        purchaseProduct.setAnnouncement(announcement.get());
        purchaseProduct.setQuantity(dto.getQuantity());
        purchaseProduct.setPurchaseOrder(purchaseOrder);

        return purchaseProduct;
    }

    public PurchaseOrderDTO getPurchaseOrder(Long id) {
        Optional<PurchaseOrder> purchaseOrder = purchaseOrderRepo.findById(id);
        return new PurchaseOrderDTO(purchaseOrder.get());
    }
}
