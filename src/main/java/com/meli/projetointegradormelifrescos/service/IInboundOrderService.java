package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import org.springframework.stereotype.Service;

import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;

@Service
public interface IInboundOrderService {

    InboundOrder createInboundOrder(InboundOrderDTO inboundOrder);

    InboundOrderDTO convertToDto(InboundOrder inboundOrder);

    InboundOrder readOrder(long id);
}
