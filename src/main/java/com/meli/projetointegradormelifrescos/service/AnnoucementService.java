package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.exception.ListIsEmptyException;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.repository.AnnoucementRepo;
import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class AnnoucementService {
    @Autowired
    private AnnoucementRepo annoucementRepo;

    /***
     *   @param message  método responsável por consultar todos os produtos
     *   @author Thaíssa Carrafa
     */

    public List<AnnoucementDTO> listAllProducts() {
        List<AnnoucementDTO> allProducts = annoucementRepo.findAll().stream()
                .map(announcement -> new AnnoucementDTO(
                        announcement.getId(),
                        announcement.getName(),
                        announcement.getPrice(),
                        announcement.getCategory(),
                        announcement.getDescription())
                )
                .collect(Collectors.toList());

        if (allProducts.isEmpty()) {
            throw new ListIsEmptyException("Nenhum produto cadastrado");
        }
        return allProducts;


        /***
         *
         * @param message     método responsável por consultar todos os produtos de determinada categoria
         * @author Thaíssa Carrafa
         * @return
         */
//    public List<Announcement> listByCategory(String category){
//
//        List<Announcement> products = annoucementRepo.findAll().stream()
//        .filter(product -> Objects.equals(product.getCategory(),category.toUpperCase()))
//
//                .collect(Collectors.toList());
//
//        if(products.isEmpty()){
//            throw new NotFoundException("No products found");
//        }
//        return products;
//    }
    }
}
