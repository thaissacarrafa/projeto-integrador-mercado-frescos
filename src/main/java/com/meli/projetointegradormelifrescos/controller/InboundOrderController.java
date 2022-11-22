package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.*;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.service.*;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    IInboundOrderService service;

    @Autowired
    IAnnoucementService annoucementService;

    @Autowired
    IPurchaseProductService purchaseProductService;

    @Autowired
    BatchService batchService;

    @PostMapping("/inboundorder")
    public ResponseEntity<List<BatchDTO>> createInboundOrder(
        @RequestBody @Valid InboundOrderDTO orderDTO,
        @RequestHeader HttpHeaders headers
    ) {
       String tokenAccess = headers.get("Authorization").toString().replace("[", "").replace("]", "");
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.createInboundOrder(tokenAccess, orderDTO));
    }

    @PutMapping("/inboundorder/{id}")
    public ResponseEntity<List<BatchDTO>> updateInboundOrder(
        @PathVariable Long id,
        @RequestBody @Valid InboundOrderDTO orderDTO,
        @RequestHeader HttpHeaders headers
    ) {
        String tokenAccess = headers.get("Authorization").toString().replace("[", "").replace("]", "");
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.updateInboundOrder(id, orderDTO, tokenAccess));
    }

    @GetMapping
    public ResponseEntity<List<AnnoucementDTO>> listAllProduct() {
        List<AnnoucementDTO> allProducts = annoucementService.listAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

    @GetMapping("/list")
    public ResponseEntity<List<AnnoucementDTO>> listByCategory(
        @RequestParam(
            value = "querytype",
            required = false,
            defaultValue = ""
        ) String category
    ) {
        return new ResponseEntity(
            annoucementService.findAllByCategory(Category.valueOf(category)),
            HttpStatus.OK
        );
    }

    @PostMapping("/orders")
    public ResponseEntity<HashMap> createPurchaseOrder(
        @RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(purchaseProductService.createPurchaseOrder(purchaseOrderDTO));
    }

    @GetMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<HashMap> getPurchaseOrder(
        @PathVariable Long purchaseOrderId
    ) {
        return ResponseEntity.ok(
            purchaseProductService.getPurchaseOrder(purchaseOrderId)
        );
    }

    @PutMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<HashMap> putPurchaseOrder(
        @PathVariable Long purchaseOrderId
    ) {
        return ResponseEntity.ok(
            purchaseProductService.putPurchaseOrder(purchaseOrderId)
        );
    }

    @GetMapping("/warehouse/{productId}")
    public ResponseEntity<WarehouseStockDTO> listProductsByWarehouse(
        @PathVariable @Valid @NotEmpty Long productId
    ) {
        return ResponseEntity.ok(
            batchService.countStocksByProductId(productId)
        );
    }

    @GetMapping("/list/{productId}")
    public ResponseEntity<BatchDTO> listProductsBySection(
        @PathVariable @Valid @NotEmpty Long productId
    ) {
        return ResponseEntity.ok(batchService.productsBySection(productId));
    }
}
