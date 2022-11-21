package com.meli.projetointegradormelifrescos.controller;


import com.meli.projetointegradormelifrescos.dto.BatchNearDueDateDTO;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/duedate")
public class getBatchNearDueDate {

    @Autowired
    BatchService batchService;

    @GetMapping("/{numberOfDays}/{section}")
    public ResponseEntity<List<BatchNearDueDateDTO>> getBatchNearDueDate(@PathVariable Integer numberOfDays, @PathVariable Long section) {
        return ResponseEntity.ok(batchService.getBatchStockBySection(numberOfDays, section));
    }
}
