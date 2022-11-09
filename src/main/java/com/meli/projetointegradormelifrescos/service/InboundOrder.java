package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.model.InboundOrder;

public interface IInboundOrder {

    InboundOrder readOrder(long id);
    InboundOrder save(InboundOrder inboundOrder);
    
}
