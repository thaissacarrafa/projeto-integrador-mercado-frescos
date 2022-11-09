package com.meli.projetointegradormelifrescos.service;

public interface InboundOrder {

    com.meli.projetointegradormelifrescos.model.InboundOrder readOrder(long id);
    com.meli.projetointegradormelifrescos.model.InboundOrder save(com.meli.projetointegradormelifrescos.model.InboundOrder inboundOrder);
    
}
