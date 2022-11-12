package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.service.IFreshProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class FreshProductsController {

        @Autowired
        private IFreshProductsService service;

        @GetMapping
        public ResponseEntity<List<AnnoucementDTO>> getAllProductsAnnoucement(){
                return ResponseEntity.ok(service.getAllProductsAnnoucement());
        }

     /*   @GetMapping("/list")
        public ResponseEntity<List<AnnoucementDTO>> getAllProductsByCategory(@RequestParam(required = false) Optional<String> category){
                String categoryBy = Optional.empty();
                if(category.isPresent()){
                        categoryBy = Optional.of(Category.valueOf(category.get()));
                }
                return ResponseEntity.ok(service.getAllProductsByCategory(categoryBy));
        } */

}
