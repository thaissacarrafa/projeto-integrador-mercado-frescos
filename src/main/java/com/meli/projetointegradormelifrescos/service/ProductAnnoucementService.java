package com.meli.projetointegradormelifrescos.service;

import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.ProductAnnoucement;
import com.meli.projetointegradormelifrescos.repository.ProductAnnoucementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductAnnoucementService {
        @Autowired
        private ProductAnnoucementRepo productRepository;

    /***
     *   @param message  método responsável por consultar todos os produtos
      */
        public List<ProductAnnoucement> listAllProducts() {
            List<ProductAnnoucement> productList = productRepository.findAll();
            if(productList.isEmpty()) {
                throw new NotFoundException("Product list is empty");
            }
            return productList;
        }

    /***
     *
     * @param message     método responsável por consultar todos os produtos
     * @return
     */
    public List<ProductAnnoucement> listByCategory(String category){

        List<ProductAnnoucement> products = productRepository.findAll().stream()
        .filter(product -> Objects.equals(product.getCategory().getProductCategory(),category.toUpperCase()))

                .collect(Collectors.toList());

        if(products.isEmpty()){
            throw new NotFoundException("No products found");
        }
        return products;
    }
}
