package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.*;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.service.*;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
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


    /***
     *
     * Requisito 01
     * @author Thaissa Carrafa, Igor Fernandes, Leonardo Correia
     * @return List<BatchDTO
     */

    @PostMapping("/inboundorder")
    public ResponseEntity<List<BatchDTO>> createInboundOrder(
        @RequestBody @Valid InboundOrderDTO orderDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.createInboundOrder(orderDTO));
    }

    /***
     *
     * Requisito 01
     * @author Thaissa Carrafa
     * @return List<BatchDTO
     */

    @PutMapping("/inboundorder/{id}")
    public ResponseEntity<List<BatchDTO>> updateInboundOrder(
        @PathVariable Long id,
        @RequestBody @Valid InboundOrderDTO orderDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(service.updateInboundOrder(id, orderDTO));
    }
    /***
     *
     * Requisito 02
     * @author Thaissa Carrafa
     * @return <List<AnnoucementDTO>
     */
    @GetMapping
    public ResponseEntity<List<AnnoucementDTO>> listAllProduct() {
        List<AnnoucementDTO> allProducts = annoucementService.listAllProducts();
        return ResponseEntity.ok().body(allProducts);
    }

    /***
     *
     * Requisito 02
     * @author Thaissa Carrafa
     * @return <List<AnnoucementDTO
     */

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

    /***
     *
     * Requisito 02
     * @author Fernanda Alcione
     * @return ResponseEntity<HashMap>
     */
    @PostMapping("/orders")
    public ResponseEntity<HashMap> createPurchaseOrder(
        @RequestBody @Valid PurchaseOrderDTO purchaseOrderDTO
    ) {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(purchaseProductService.createPurchaseOrder(purchaseOrderDTO));
    }
    /***
     *
     * Requisito 02
     * @author Fernanda Alcione
     * @return ResponseEntity<HashMap>
     */

    @GetMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<HashMap> getPurchaseOrder(
        @PathVariable Long purchaseOrderId
    ) {
        return ResponseEntity.ok(
            purchaseProductService.getPurchaseOrder(purchaseOrderId)
        );
    }

    /***
     *
     * Requisito 02
     * @author Fernanda Alcione
     * @return ResponseEntity<HashMap>
     */
    @PutMapping("/orders/{purchaseOrderId}")
    public ResponseEntity<HashMap> putPurchaseOrder(
        @PathVariable Long purchaseOrderId
    ) {
        return ResponseEntity.ok(
            purchaseProductService.putPurchaseOrder(purchaseOrderId)
        );
    }

    /***
     *
     * Requisito 03
     * @author Thaissa Carrafa, Amanda Lobo e Leonardo Correia
     * @return ResponseEntity<WarehouseStockDTO>
     */
    @GetMapping("/warehouse/{productId}")
    public ResponseEntity<WarehouseStockDTO> listProductsByWarehouse(
        @PathVariable @Valid @NotEmpty Long productId
    ) {
        return ResponseEntity.ok(
            batchService.countStocksByProductId(productId)
        );
    }
    /***
     *
     * Requisito 04
     * @author Thaissa Carrafa
     * @return ResponseEntity<BatchDTO>
     */
    @GetMapping("/list/{productId}")
    public ResponseEntity<BatchDTO> listProductsBySection(
        @PathVariable @Valid @NotEmpty Long productId
    ) {
        return ResponseEntity.ok(batchService.productsBySection(productId));
    }
}
