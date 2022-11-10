package com.meli.projetointegradormelifrescos.controller;


import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;

import javax.validation.Valid;


public class InboundOrderController {
    @Autowired
    private IInboundOrderService service;

    @GetMapping("/inboundorder/")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        InboundOrder order = service.readOrder(id);
        if(order != null) {
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.notFound().build();
    }

    /***
     *
     * Cadastre um lote com o estoque de produtos que o compõe. Devolva o lote criado com o código de status "201 CREATED".
     * @param dto
     * @return
    ***/

    @PostMapping("/inboundorder")
    public ResponseEntity<InboundOrderDTO> createInboundOrder(@RequestBody @Valid InboundOrderDTO inboundOrderDTO) {
        InboundOrder inboundOrder = service.createInboundOrder(inboundOrderDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.convertToDto(inboundOrder));
    }

    }

