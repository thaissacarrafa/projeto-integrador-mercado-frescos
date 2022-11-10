package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.InboundOrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/")
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

    /***
     *
     * Cadastre um lote com o estoque de produtos que o compõe. Devolva o lote criado com o código de status "201 CREATED".
     * @param dto
     * @return
     */
    @PostMapping
    @ApiOperation(value = "Cadastra um lote de produtos.")
    ResponseEntity<InboundOrder> insertInboundOrder(@RequestBody @Valid InboundOrder inboundOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inboundOrder));
    }



    }

