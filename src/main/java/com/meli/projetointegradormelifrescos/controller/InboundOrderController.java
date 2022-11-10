package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.IInboundOrder;

import java.util.List;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/")
@Api(value = "API Meli Frescos")
@CrossOrigin(origins = "*")
public class InboundOrderController {
    @Autowired
    private IInboundOrder service;

    @GetMapping("{id}")
    @ApiOperation(value = "Retorna uma lista de lotes de acordo com determinado ID")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.readOrder(id));
    }

    /***
     *
     * Cadastre um lote com o estoque de produtos que o compõe. Devolva o lote criado com o código de status "201 CREATED".
     * @param dto
     * @return
     */
    @PostMapping
    public ResponseEntity<List<BatchStockReqDTO>> insertOrder(@RequestBody InboundOrderReqDTO orderReqDTO) {
        List<BatchStockReqDTO> batches = service.save(orderReqDTO);

        return new ResponseEntity<List<BatchStockReqDTO>>(batches, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Cadastra um lote de produtos.")
    ResponseEntity<InboundOrder> insertInboundOrder(@RequestBody @Valid InboundOrder inboundOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inboundOrder));
    }

    }

