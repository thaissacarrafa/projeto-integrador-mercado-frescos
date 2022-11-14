package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.service.IFreshProductsService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FreshProductsService implements IFreshProductsService {

    @Override
    public List<AnnoucementDTO> getAllProductsAnnoucement() {
        return null;
    }

    @Override
    public List<AnnoucementDTO> getAllProductsByCategory(Optional<Category> category) {
        return null;
    }

}
