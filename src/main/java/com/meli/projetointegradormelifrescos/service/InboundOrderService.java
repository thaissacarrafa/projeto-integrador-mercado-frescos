package com.meli.projetointegradormelifrescos.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.dto.Output.InboundOrderResDTO;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;

@Service
public class InboundOrderService implements IInboundOrder {

    @Autowired
    private InboundOrderRepo repo;

    @Override
    public InboundOrder readOrder(long id) {
        return repo.getById(id);
    }

    @Override
    public List<BatchStockReqDTO> save(InboundOrderReqDTO inboundOrderReqDTO) {
        InboundOrder order = new InboundOrder();
        order.setOrderDate(inboundOrderReqDTO.getOrderDate());
        order.setBatches(inboundOrderReqDTO.getBatches());

        repo.save(order);

        return order.getBatches();
    }
}
