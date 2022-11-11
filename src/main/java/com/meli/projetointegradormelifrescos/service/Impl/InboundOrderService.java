package com.meli.projetointegradormelifrescos.service;


import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;


import java.util.List;
import java.util.Optional;

import com.meli.projetointegradormelifrescos.model.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.dto.Input.BatchStockReqDTO;
import com.meli.projetointegradormelifrescos.dto.Input.InboundOrderReqDTO;

import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import org.springframework.stereotype.Service;


@Service
public class InboundOrderService implements IInboundOrderService {

    @Autowired
    private IInboundOrderRepository repo;

    @Override
    public InboundOrderDTO createInboundOrder(InboundOrderDTO dto) {

        // se o armazém é válido
        Warehouse warehouse = validWarehouse(dto.getWarehouseCode());

        // e que o representante pertence ao armazém
        Representant representant = findRepresentantFromWarehouse(warehouse, dto.getRepresentantId());

        // e que o setor é valido
        Section section = findSectionByCode(warehouse, dto.getSectionCode());

        // e que o produto pertence ao setor - verificar por temperatura
        dto.getBatchStock().stream().forEach(batch -> {
            isThisBatchBelongToSection(batch, section);
        });

        isTheSectionHasEnoughtSpace(section, dto);

        InboundOrder inboundOrder = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(dto);
        inboundOrder.setWarehouse(warehouse);
        inboundOrder.setRepresentant(representant);
        inboundOrder.setSection(section);
        repo.save(inboundOrder);
        dto.getBatchStock().forEach(b -> saveBatchStock(b, inboundOrder));
        return dto;
    }

    private Warehouse validWarehouse(Long warehouseCode) {
        Optional<Warehouse> warehouse = warehouseRepo.findWarehouseByCode(warehouseCode);
        if (warehouse.isEmpty()) {
            throw new BadRequestException("Invalid warehouse Code");
        }
        return warehouse.get();
    }
}