package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.ProductAnnoucementDTO;
import com.meli.projetointegradormelifrescos.model.ProductAnnoucement;
import com.meli.projetointegradormelifrescos.service.ProductAnnoucementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



public class ProductAnnoucementController {
/*
    @Autowired
    private ProductAnnoucementService productService;

    /***
     * Veja uma lista completa de produtos.
     * Se a lista não existir, ela deve retornar um "404 Not Found".
     * @return

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de produtos.")
    public ResponseEntity<List<ProductAnnoucementDTO>> all(){
        List<ProductAnnoucement> all = this.productService.listAllProducts();
        return ResponseEntity.ok(ProductAnnoucementDTO.convertListProducts(all));
    }


     *Veja uma lista de produtos por categoria.Se a lista não existir,
     * ela deve retornar um "404 Not Found".
     * @return

    @GetMapping("/list")
    @ApiOperation(value = "Retorna uma lista de produtos de determinada categoria.")
    public ResponseEntity<List<ProductAnnoucementDTO>> getByCategory(@RequestParam (required = false) String category){

        return new ResponseEntity(productService.listByCategory(category), HttpStatus.OK);
    }

*/
}
