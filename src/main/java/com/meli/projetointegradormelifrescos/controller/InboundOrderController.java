package com.meli.projetointegradormelifrescos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.InboundOrderService;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {
    @Autowired
    private InboundOrderService service;

    @GetMapping("{id}")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        InboundOrder order = service.readOrder(id);
        if(order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<InboundOrder> insert(@RequestBody InboundOrder inboundOrder) {
        return ResponseEntity.ok(service.save(inboundOrder));
    }
}
