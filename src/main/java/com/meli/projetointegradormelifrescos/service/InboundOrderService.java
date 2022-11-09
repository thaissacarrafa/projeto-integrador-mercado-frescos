package com.meli.projetointegradormelifrescos.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.projetointegradormelifrescos.dto.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderResDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;

@Service
public class InboundOrderService implements IInboundOrder {

    private InboundOrderRepo repo;

    @Override
    public InboundOrder readOrder(long id) {
        return repo.getById(id);
    }

    @Override
    public InboundOrder save(InboundOrderReqDTO inboundOrderReqDTO) {
        InboundOrder order = new InboundOrder();

        return repo.save(order);
    }
}
