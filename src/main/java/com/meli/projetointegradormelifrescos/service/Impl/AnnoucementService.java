package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.projetointegradormelifrescos.config.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.enums.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.enums.repository.AnnoucementRepo;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.service.IAnnoucementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnnoucementService implements IAnnoucementService {
        @Autowired
        private AnnoucementRepo productRepository;

        @Autowired
        private WarehouseRepo warehouseRepo;

    /***
     *   @param message  método responsável por consultar todos os produtos
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
     * @param message     método responsável por consultar todos os produtos
     * @return
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
//
//    /**
//     * Verifica a data de validade (se é superior a 3 semanas)
//     * @author Amanda Lobo
//     * @param batch -> Batch
//     * @param idProduct -> Long
//     * @throws NotFoundException
//     */
//    public static void verifyProductDueDate(Batch batch, Long idProduct){
//        if(batch.getDueDate().isBefore(LocalDate.now().plusWeeks(3))){
//            throw new NotFoundException("expired product");
//        }
}
