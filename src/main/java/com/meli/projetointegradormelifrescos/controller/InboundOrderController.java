package com.meli.projetointegradormelifrescos.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.service.IInboundOrder;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/inboundorder/")
public class InboundOrderController {
    @Autowired
    private IInboundOrder service;

    @GetMapping("{id}")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.readOrder(id));
    }

    @PostMapping
    public ResponseEntity<List<BatchStockReqDTO>> insertOrder(@RequestBody InboundOrderReqDTO orderReqDTO) {
        List<BatchStockReqDTO> batches = service.save(orderReqDTO);

        return new ResponseEntity<List<BatchStockReqDTO>>(batches, HttpStatus.CREATED);
    }
}
