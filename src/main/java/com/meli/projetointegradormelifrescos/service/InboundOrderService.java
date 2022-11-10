package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import org.springframework.beans.factory.annotation.Autowired;

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
}