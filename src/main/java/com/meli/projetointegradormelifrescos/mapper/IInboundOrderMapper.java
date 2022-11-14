package com.meli.projetointegradormelifrescos.mapper;

import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
@Mapper
public interface IInboundOrderMapper {
    IInboundOrderMapper MAPPER = Mappers.getMapper(IInboundOrderMapper.class);

    InboundOrder mappingInboundOrderDTOToInboundOrder(InboundOrderDTO dto);
}
