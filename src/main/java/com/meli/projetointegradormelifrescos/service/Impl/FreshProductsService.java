package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.ProductAnnoucementDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreshProductsService implements IFreshProductsService {

    @Override
    public List<ProductAnnoucementDTO> getAllProductsAnnoucement() {
        return null;
    }

    @Override
    public List<ProductAnnoucementDTO> getAllProductsByCategory(Optional<Category> category) {
        return null;
    }

}
