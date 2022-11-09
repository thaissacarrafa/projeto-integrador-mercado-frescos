package com.meli.projetointegradormelifrescos.service;

import org.springframework.stereotype.Service;

import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;

@Service
public class InboundOrderService implements InboundOrder {

    private InboundOrderRepo repo;

    @Override
    public com.meli.projetointegradormelifrescos.model.InboundOrder readOrder(long id) {
        return repo.getById(id);
    }

    @Override
    public com.meli.projetointegradormelifrescos.model.InboundOrder save(com.meli.projetointegradormelifrescos.model.InboundOrder inboundOrder) {
        return repo.save(inboundOrder);
    }
}
