package com.meli.projetointegradormelifrescos.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.InboundOrderService;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
@Api(value = "API Meli Frescos")
@CrossOrigin(origins = "*")
public class InboundOrderController {
    @Autowired
    private InboundOrderService service;

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna uma lista de lotes de acordo com determinado ID")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        InboundOrder order = service.readOrder(id);
        if(order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ApiOperation(value = "Cadastra um lote de produtos.")
    ResponseEntity<InboundOrder> insert(@RequestBody InboundOrder inboundOrder) {
        return ResponseEntity.ok(service.save(inboundOrder));
    }
}
