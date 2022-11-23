package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.service.IBatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class BatchStockController {

    @Autowired
    private IBatchService service;

    @GetMapping("/{numberOfDays}/{section}")
    @ResponseBody
    public ResponseEntity<BatchStockResDTO> getByBatchStockBySection(
        @PathVariable Integer numberOfDays,
        @PathVariable Long section
    ) {
        return ResponseEntity.ok(
            service.getBatchStockBySection(numberOfDays, section)
        );
    }

    @GetMapping("/list/{numberOfDays}/{category}/{sortBy}")
    @ResponseBody
    public ResponseEntity<BatchStockResDTO> getByBatchStockByCategory(
        @PathVariable Integer numberOfDays,
        @PathVariable String category,
        @PathVariable String sortBy
    ) {
        return ResponseEntity.ok(
            service.getBatchStockByCategory(numberOfDays, category, sortBy)
        );
    }
}
