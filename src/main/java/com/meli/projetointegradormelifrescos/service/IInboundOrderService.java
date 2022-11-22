package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import java.util.List;

public interface IInboundOrderService {
    List<BatchDTO> createInboundOrder(String tokenAccess, InboundOrderDTO orderDTO);

    List<BatchDTO> updateInboundOrder(Long orderId, InboundOrderDTO orderDTO, String tokenAccess);
}
