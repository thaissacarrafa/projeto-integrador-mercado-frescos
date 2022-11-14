package com.meli.projetointegradormelifrescos.mapper;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.model.Batch;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IBatchStockMapper {
    IBatchStockMapper MAPPER = Mappers.getMapper(IBatchStockMapper.class);
    Batch mappingBatchStockDTOToBatchStock(BatchDTO dto);
    List<BatchDTO> map(List<Batch> batches);

}
