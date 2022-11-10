package com.meli.projetointegradormelifrescos.service;


import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;


public class InboundOrderService implements IInboundOrderService {

    @Autowired
    private InboundOrderRepo repo;
    @Override
    public InboundOrder createInboundOrder(InboundOrderDTO inboundOrder) {


        return null;
    }

    @Override
    public InboundOrderDTO convertToDto(InboundOrder inboundOrder) {



        return null;
    }

    @Override

    public InboundOrder readOrder(long id) {
        return null;
    }

    public List<BatchStockReqDTO> save(InboundOrderReqDTO inboundOrderReqDTO) {
        InboundOrder order = new InboundOrder();
        order.setOrderDate(inboundOrderReqDTO.getOrderDate());
        order.setBatches(inboundOrderReqDTO.getBatches());

        repo.save(order);

        return order.getBatches();
    }

    public com.meli.projetointegradormelifrescos.model.InboundOrder save(com.meli.projetointegradormelifrescos.model.InboundOrder inboundOrder) {
        return repo.save(inboundOrder);

    }
}