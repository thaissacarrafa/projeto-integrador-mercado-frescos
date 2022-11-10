package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.ProductAnnoucementDTO;
import com.meli.projetointegradormelifrescos.enums.Category;

import java.util.List;
import java.util.Optional;

public interface IFreshProductsService {

    List<ProductAnnoucementDTO> getAllProductsAnnoucement();

    List<ProductAnnoucementDTO> getAllProductsByCategory(Optional<Category> category);
}