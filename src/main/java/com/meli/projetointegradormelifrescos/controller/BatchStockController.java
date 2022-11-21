package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.service.IBatchStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class BatchStockController {

    @Autowired
    private IBatchStockService service;

    @GetMapping
    @ResponseBody
    public ResponseEntity<BatchStockResDTO> getByBatchStockBySection(
        @RequestParam Integer numberOfDays,
        @RequestParam Long section
    ) {
        return ResponseEntity.ok(
            service.getBatchStockBySection(numberOfDays, section)
        );
    }

    @GetMapping("/list")
    @ResponseBody
    public ResponseEntity<BatchStockResDTO> getByBatchStockByCategory(
        @RequestParam Integer numberOfDays,
        @RequestParam String category,
        @RequestParam String sortBy
    ) {
        return ResponseEntity.ok(
            service.getBatchStockByCategory(numberOfDays, category, sortBy)
        );
    }
}
