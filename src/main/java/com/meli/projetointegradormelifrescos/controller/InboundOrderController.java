package com.meli.projetointegradormelifrescos.controller;


import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@RestController
    @RequestMapping("/api/v1/fresh-products")
    public class InboundOrderController {

        @Autowired
        IInboundOrderService service;

        @PostMapping("/inboundorder")
        public ResponseEntity<List<BatchDTO>> createInboundOrder(@RequestBody @Valid InboundOrderDTO dto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.createInboundOrder(dto));
        }

    }


