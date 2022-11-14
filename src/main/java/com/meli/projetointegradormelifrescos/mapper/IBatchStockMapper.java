package com.meli.projetointegradormelifrescos.mapper;

import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.model.BatchStock;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface IBatchStockMapper {
    IBatchStockMapper MAPPER = Mappers.getMapper(IBatchStockMapper.class);
    BatchStock mappingBatchStockDTOToBatchStock(BatchStockDTO dto);
    List<BatchStockDTO> map(List<BatchStock> batches);

}
