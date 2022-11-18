package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;
import com.meli.projetointegradormelifrescos.service.IFreshProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
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

        @PostMapping("/orders")
        public ResponseEntity<HashMap> createPurchaseOrder(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO) {
                return ResponseEntity.status(HttpStatus.CREATED).body(service.createPurchaseOrder(purchaseOrderDTO));
        }

        @GetMapping("/orders/{purchaseOrderId}")
        public ResponseEntity<PurchaseOrderDTO> getPurchaseOrder(@PathVariable Long purchaseOrderId) {
                return ResponseEntity.ok(service.getPurchaseOrder(purchaseOrderId));
        }
}
