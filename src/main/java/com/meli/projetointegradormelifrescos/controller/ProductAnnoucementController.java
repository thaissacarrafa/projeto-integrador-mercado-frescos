package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.ProductAnnoucementDTO;
import com.meli.projetointegradormelifrescos.model.ProductAnnoucement;
import com.meli.projetointegradormelifrescos.service.ProductAnnoucementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/fresh-products")
public class ProductAnnoucementController {


    @Autowired
    private ProductAnnoucementService productService;

    @GetMapping
    public ResponseEntity<List<ProductAnnoucementDTO>> all(){
        List<ProductAnnoucement> all = this.productService.listAllProducts();
        return ResponseEntity.ok(ProductAnnoucementDTO.convertListProducts(all));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductAnnoucementDTO>> getByCategory(@RequestParam String category){

        return new ResponseEntity(productService.listByCategory(category), HttpStatus.OK);
    }


}
