package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.dto.PurchaseOrderDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.service.AnnoucementService;
import com.meli.projetointegradormelifrescos.service.IPurchaseProductService;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    IInboundOrderService service;

    @Autowired
    AnnoucementService annoucementService;

    @Autowired
    private IPurchaseProductService purchaseProductService;


    @PostMapping("/inboundorder")
    public ResponseEntity<List<BatchDTO>> createInboundOrder(@RequestBody @Valid InboundOrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createInboundOrder(orderDTO));
    }

    @PutMapping("/inboundorder/{id}")
    public ResponseEntity<List<BatchDTO>> updateInboundOrder(
        @PathVariable Long id,
        @RequestBody @Valid InboundOrderDTO orderDTO) {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.updateInboundOrder(id, orderDTO));
    }

    @GetMapping
    public ResponseEntity<List<AnnoucementDTO>> listAllProduct(){
        List<AnnoucementDTO> allProducts = annoucementService.listAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AnnoucementDTO>> listByCategory(
            @RequestParam(value = "querytype", required = false, defaultValue = "") String category){
        return new ResponseEntity(annoucementService.findAllByCategory(Category.valueOf(category)), HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<HashMap> createPurchaseOrder(@RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(purchaseProductService.createPurchaseOrder(purchaseOrderDTO));
    }

    @GetMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<HashMap> getPurchaseOrder(@PathVariable Long purchaseOrderId) {
        return ResponseEntity.ok(purchaseProductService.getPurchaseOrder(purchaseOrderId));
    }
}
