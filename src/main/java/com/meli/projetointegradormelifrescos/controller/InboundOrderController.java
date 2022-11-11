package com.meli.projetointegradormelifrescos.controller;


//import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.model.Batch;

import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
//import com.meli.projetointegradormelifrescos.model.InboundOrder;

//import com.meli.projetointegradormelifrescos.service.IInboundOrder;

import java.util.List;


import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/fresh-products/")
public class InboundOrderController {
    @Autowired
    private IInboundOrderService service;

   // private IInboundOrder service;

   /* @GetMapping("/inboundorder/")
    public ResponseEntity<InboundOrder> getById(@PathVariable long id) {
        return ResponseEntity.ok(service.readOrder(id));
    }*/

    /***
     *
     * Cadastre um lote com o estoque de produtos que o compõe. Devolva o lote criado com o código de status "201 CREATED".
     * @param inboundOrderDTO
     * @return
    ***/

    @PostMapping(value="inboundorder")
    public ResponseEntity<List<Batch>> createInboundOrder(@RequestBody InboundOrderReqDTO inboundOrderDTO) {
       // InboundOrder inboundOrder = service.createInboundOrder(inboundOrderDTO);
        System.out.println("cheguei");
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inboundOrderDTO));
    }

    /*
    @PostMapping
    public ResponseEntity<List<BatchStockReqDTO>> insertOrder(@RequestBody InboundOrderReqDTO orderReqDTO) {
        List<BatchStockReqDTO> batches = service.save(orderReqDTO);

        return new ResponseEntity<List<BatchStockReqDTO>>(batches, HttpStatus.CREATED);
    }

   ResponseEntity<InboundOrder> insertInboundOrder(@RequestBody @Valid InboundOrder inboundOrder) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(inboundOrder));

    }*/

    }

