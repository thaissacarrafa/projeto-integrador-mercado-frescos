package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseDTO;
import com.meli.projetointegradormelifrescos.service.InboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
     * @return retorna um BatchDTO
     * @throws Exception
     */
    @GetMapping("/{productId}")
    public ResponseEntity<List<WarehouseDTO>> getAllProductWarehouse(@PathVariable Long productId) throws Exception {
        return new ResponseEntity(inboundOrderService.getAllProductWarehouse(productId), HttpStatus.OK);
    }

    /**
     * ordenação de lotes por parâmetro (L= lote, Q= quantidade, V = validade).
     * @author Amanda Lobo
     * @param productId -> Long
     * @param sorting -> String
     * @return List<BatchDTO>
     * @throws Exception
     */
    @GetMapping({"/{productId}/{sorting}"})
    public ResponseEntity<List<WarehouseDTO>> getAllOrdinancesForBatches(
        @PathVariable("productId") Long productId,
        @PathVariable("sorting") String sorting) throws Exception {
        List<WarehouseDTO> warehouseDTOList = inboundOrderService.getAllProductWarehouse(productId);
        return new ResponseEntity(inboundOrderService.getAllOrdinancesFotBatches(warehouseDTOList, sorting), HttpStatus.OK);
    }
}
