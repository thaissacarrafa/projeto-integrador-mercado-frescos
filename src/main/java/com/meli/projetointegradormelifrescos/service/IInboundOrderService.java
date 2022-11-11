package com.meli.projetointegradormelifrescos.service;


import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface IInboundOrderService {
    public List<Batch> save(InboundOrderReqDTO inboundOrder);
    //InboundOrder createInboundOrder(InboundOrderReqDTO inboundOrder);

    // InboundOrderDTO convertToDto(InboundOrder inboundOrder);

    //InboundOrder readOrder(long id);
}
