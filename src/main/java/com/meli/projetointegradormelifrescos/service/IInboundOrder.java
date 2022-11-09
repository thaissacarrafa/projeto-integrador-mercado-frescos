package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderResDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;

public interface IInboundOrder {

    InboundOrder readOrder(long id);
    InboundOrderResDTO save(InboundOrderReqDTO inboundOrderDTO);
    
}
