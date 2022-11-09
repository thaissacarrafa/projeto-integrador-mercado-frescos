package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.ProductAnnoucementDTO;
import com.meli.projetointegradormelifrescos.model.ProductAnnoucement;
import com.meli.projetointegradormelifrescos.service.ProductAnnoucementService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/fresh-products")
@Api(value = "Meli Frescos API REST")
@CrossOrigin(origins = "*") // liberar todos os domínios para acessar a api
public class ProductAnnoucementController {

    @Autowired
    private ProductAnnoucementService productService;

    @GetMapping
    @ApiOperation(value = "Retorna uma lista de produtos.")
    public ResponseEntity<List<ProductAnnoucementDTO>> all(){
        List<ProductAnnoucement> all = this.productService.listAllProducts();
        return ResponseEntity.ok(ProductAnnoucementDTO.convertListProducts(all));
    }

    @GetMapping("/list")
    @ApiOperation(value = "Retorna uma lista de produtos de determinada categoria.")
    public ResponseEntity<List<ProductAnnoucementDTO>> getByCategory(@RequestParam String category){

        return new ResponseEntity(productService.listByCategory(category), HttpStatus.OK);
    }


}
