package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.BatchStockResDTO;
import com.meli.projetointegradormelifrescos.service.IBatchService;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fresh-products/due-date")
public class BatchStockController {

    @Autowired
    private IBatchService service;

    /**
     *
     * Obtenha todos os lotes armazenados em um setor de um armazém ordenados por sua data de vencimento.
     * @author Gustavo Dolzan
     * @return
     */

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

    /**
     *
     * Obtenha uma lista de lotes dentro do prazo de validade solicitado, que pertencem a uma determinada
     * categoria de produto: FRESCO, REFRIGERADO, CONGELADO
     * @author Igor Fernandes
     * @return
     */

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

    @GetMapping("/expired")
    @ResponseBody
    public ResponseEntity<BatchStockResDTO> getExpiredBatches(
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam(
            required = false
        ) LocalDate dueDate,
        @RequestParam(required = false) String category,
        @RequestParam(required = false) String sortBy
    ) {
        return ResponseEntity.ok(
            service.getExpiredBatches(
                dueDate != null ? dueDate : LocalDate.now(),
                category != null ? category : "all",
                sortBy != null ? sortBy : "asc"
            )
        );
    }
}
