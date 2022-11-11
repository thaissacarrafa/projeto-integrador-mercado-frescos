package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products")
public class InboundOrderController {

    @Autowired
    IInboundOrderService service;

    @PostMapping("/inboundorder")
    public ResponseEntity<InboundOrderDTO> createInboundOrder(@RequestBody @Valid InboundOrderDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createInboundOrder(dto));
    }

}
