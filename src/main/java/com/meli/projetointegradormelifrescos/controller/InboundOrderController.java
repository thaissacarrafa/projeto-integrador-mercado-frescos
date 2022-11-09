package com.meli.projetointegradormelifrescos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.dto.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderResDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.IInboundOrder;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {
    @Autowired
    private IInboundOrder service;

    @GetMapping("{id}")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        InboundOrder order = service.readOrder(id);
        if(order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    ResponseEntity<InboundOrderResDTO> insert(@RequestBody InboundOrderReqDTO inboundOrderReqDTO) {
        return ResponseEntity.ok(service.save(inboundOrderReqDTO));
    }
}
