package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.enums.Sorting;
import com.meli.projetointegradormelifrescos.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/list")
public class LocationController {

    @Autowired
    private InboundOrderService inboundOrderService;

    /**
     * retorna uma lista de produtos com todos os lotes que ele aparece
     * @author Amanda Lobo
     * @param productId -> Long
     * @return retorna um WarehouseDTO
     * @throws Exception*/
    @GetMapping("/{productId}")
    public ResponseEntity<List<BatchDTO>> getAllProductWarehouse(@RequestParam Long productId) throws Exception {
        return new ResponseEntity(inboundOrderService.getAllProductWarehouse(productId), HttpStatus.OK);
    }
}
