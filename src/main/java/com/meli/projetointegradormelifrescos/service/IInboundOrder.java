package com.meli.projetointegradormelifrescos.service;

import java.util.List;

import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;

public interface IInboundOrder {

    InboundOrder readOrder(long id);
    List<BatchStockReqDTO> save(InboundOrderReqDTO inboundOrderDTO);
    
}
