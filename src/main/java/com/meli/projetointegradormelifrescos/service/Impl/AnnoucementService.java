package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.enums.repository.AnnoucementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AnnoucementService {
        @Autowired
        private AnnoucementRepo productRepository;

    /***
     *   message  método responsável por consultar todos os produtos
     * @return List<Announcement>
     */
        public List<Announcement> listAllProducts() {
            List<Announcement> productList = productRepository.findAll();
            if(productList.isEmpty()) {
                throw new NotFoundException("Product list is empty");
            }
            return productList;
        }

    /***
     *
     * @param category método responsável por consultar todos os produtos
     * @return List<Announcement>
     */
    public List<Announcement> listByCategory(String category){

        List<Announcement> products = productRepository.findAll().stream()
        .filter(product -> Objects.equals(product.getCategory(),category.toUpperCase()))

                .collect(Collectors.toList());

        if(products.isEmpty()){
            throw new NotFoundException("No products found");
        }
        return products;
    }
}