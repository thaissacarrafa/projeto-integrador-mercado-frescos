package com.meli.projetointegradormelifrescos.mapper;

import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.model.BatchStock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface IBatchStockMapper {
    IBatchStockMapper MAPPER = Mappers.getMapper(IBatchStockMapper.class);

    BatchStock mappingBatchStockDTOToBatchStock(BatchStockDTO dto);
}
