package com.meli.projetointegradormelifrescos.service.Impl;


import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;


import java.util.Optional;

import com.meli.projetointegradormelifrescos.mapper.IBatchStockMapper;
import com.meli.projetointegradormelifrescos.mapper.IInboundOrderMapper;
import com.meli.projetointegradormelifrescos.model.*;
import com.meli.projetointegradormelifrescos.repository.BatchStockRepo;
import com.meli.projetointegradormelifrescos.repository.SectionRepo;
import com.meli.projetointegradormelifrescos.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;


import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import org.springframework.stereotype.Service;


@Service
public class InboundOrderService implements IInboundOrderService {

    @Autowired
    private InboundOrderRepo inboundOrderRepo;
    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private SectionRepo sectionRepo;
    @Autowired
    private BatchStockRepo batchStockRepo;

    @Override
    public InboundOrderDTO createInboundOrder(InboundOrderDTO inboundOrderDTO) {

        // se o armazém é válido
        Warehouse warehouse = validWarehouse(inboundOrderDTO.getWarehouseCode());

        // e que o representante pertence ao armazém
        Manager manager = findManagerFromWarehouse(warehouse, inboundOrderDTO.getManagerId());

        // e que o setor é valido
        Section section = findSectionByCode(warehouse, inboundOrderDTO.getSectionCode());

        // E que o setor corresponde ao tipo de produto

        inboundOrderDTO.getBatchStock().stream().forEach(batch -> {
            sectorIsEqualsBatch(batch, section);
        });

        // e que a section tem espaco
        ifTheSectionHasCapacity(section, inboundOrderDTO);



        InboundOrder inboundOrder = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(inboundOrderDTO);
        inboundOrder.setWarehouse(warehouse);
        inboundOrder.setManager(manager);
        inboundOrder.setSection(section);
        inboundOrderRepo.save(inboundOrder);
        inboundOrderDTO.getBatchStock().forEach(b -> saveBatchStock(b, inboundOrder));
        return inboundOrderDTO;

    }
        private void saveBatchStock(BatchStockDTO dto, InboundOrder inboundOrder) {
            BatchStock batchStock = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(dto);
            batchStock.setInboundOrder(inboundOrder);
            batchStock.setSection(inboundOrder.getSection());
            batchStockRepo.save(batchStock);
        }

    /***
     *
     *  método responsável por validar se a warehouse é válida, identificando
     *  se ele é ou nao vazio
     * @return
     */
    private Warehouse validWarehouse(Long warehouseCode) {
        Optional<Warehouse> warehouse = warehouseRepo.findWarehouseByCode(warehouseCode);
        if (warehouse.isEmpty()) {
            throw new NotFoundException("Invalid warehouse Code");
        }
        return warehouse.get();
    }

    /***
     * método responsável por validar se o representante pertence ao armazém
     ainda falta fazer o Manager repo ou tentar outra forma de validar se o representante é daquele armazem
     quem sabe usando stream pra mapear a lista de representantes do armazem e verificar se é igual o informado.
     @param warehouse
     @param warehouse, id
     ***/
    private Manager findManagerFromWarehouse(Warehouse warehouse, Long managerid) {
        Optional<Manager> manager = warehouse.getManagers()
                .stream()
                .filter(m -> m.getId().equals(managerid)).findFirst();
        if (manager.isEmpty()) {
            throw new BadRequestException("Invalid warehouse Id");
        }
        return manager.get();
    }


    /***
     * metodo que valida se o setor é o correto
     * @return
     */
    private Section findSectionByCode(Warehouse warehouse, Long id) {
        Optional<Section> section = warehouse.getSections()
                .stream()
                .filter(s -> s.getId().equals(id)).findFirst();
        if (section.isEmpty()) {
            throw new BadRequestException("Invalid warehouse Id");
        }
        return section.get();
    }


    /***
     * que o setor corresponde aos tipos de produto - presumindo que todo o lote vem com o mesmo tipo de categoria
     */
     private void sectorIsEqualsBatch(BatchStockDTO batch, Section section) {
            Float maximumTemperature = section.getCategory().getMaximumTemperature();
            Float minimumTemperature = section.getCategory().getMinimumTemperature();
            Float batchCurrentTemperature = batch.getCurrentTemperature();

            if (batchCurrentTemperature > maximumTemperature || batchCurrentTemperature < minimumTemperature) {
                throw new BadRequestException("Batch doesn't belong to the section.");
            }
        }

    /***
     * que o setor tem espaço pra colocar o lote
     */

    private void ifTheSectionHasCapacity(Section section, InboundOrderDTO inbound) {
        Float maxCapacity = section.getMaxCapacity();
        int currentCapacity = section.getBatchStocks().size();
        Float availableCapacity = maxCapacity - currentCapacity;
        int neededCapacity = inbound.getBatchStock().size();
        boolean dontHaveCapacity = availableCapacity < neededCapacity;
        if (dontHaveCapacity) {
            throw new BadRequestException("Section don't have enought space.");
        }
    }
}



