package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;
import com.meli.projetointegradormelifrescos.enums.Category;

import java.util.List;
import java.util.Optional;

public interface IFreshProductsService {

    List<AnnoucementDTO> getAllProductsAnnoucement();

    List<AnnoucementDTO> getAllProductsByCategory(Optional<Category> category);

    Integer createPurchaseOrder(PurchaseOrderDTO purchaseOrderDTO);
}