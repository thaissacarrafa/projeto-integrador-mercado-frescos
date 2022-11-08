package com.meli.projetointegradormelifrescos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;

@Service
public class InboundOrderService implements IInboundOrder {
    @Autowired
    private InboundOrderRepo repo;

    @Override
    public InboundOrder readOrder(long id) {
        return repo.readOrder(id);
    }

    @Override
    public InboundOrder save(InboundOrder inboundOrder) {
        return repo.save(inboundOrder);
    }
}
