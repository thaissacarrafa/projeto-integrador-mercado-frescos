package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.service.IFreshProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class FreshProductsController {

        @Autowired
        private IFreshProductsService service;

        @GetMapping
        public ResponseEntity<List<AnnoucementDTO>> getAllProductsAnnoucement() {
                return ResponseEntity.ok(service.getAllProductsAnnoucement());
        }

}
