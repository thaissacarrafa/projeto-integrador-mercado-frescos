
package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;

import java.util.List;
import java.util.Optional;
import com.meli.projetointegradormelifrescos.mapper.IBatchStockMapper;
import com.meli.projetointegradormelifrescos.mapper.IInboundOrderMapper;
import com.meli.projetointegradormelifrescos.model.*;
import com.meli.projetointegradormelifrescos.repository.*;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;


@Service
public class InboundOrderService implements IInboundOrderService {

    @Autowired
    private InboundOrderRepo inboundOrderRepo;

    @AutoConfigureOrder
    private InboundOrder inboundOrder;
    @Autowired
    private WarehouseRepo warehouseRepo;

    @Autowired
    private SectionRepo sectionRepo;
    @Autowired
    private BatchStockRepo batchStockRepo;
    @Autowired
    private ManagerRepo managerRepo;

    @Autowired
    private ModelMapper modelMapper;


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


        InboundOrderDTO convertEntityToDTO = modelMapper.map(inboundOrder, InboundOrderDTO.class);

//        InboundOrder inboundOrder = IInboundOrderMapper.MAPPER.mappingInboundOrderDTOToInboundOrder(inboundOrderDTO);
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
     * @author Thaíssa Carrafa
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
     @author Thaíssa Carrafa
     ***/
    private Manager findManagerFromWarehouse(Warehouse warehouse, Long managerid) {

        if (!warehouse.getManagers().getId().equals(managerid)) {
            throw new BadRequestException("Invalid Manager Id");
        }

        return managerRepo.findManagerById(managerid);
    }


    /***
     * metodo que valida se o setor é o correto
     * @return
     * @author Thaíssa Carrafa
     */
    private Section findSectionByCode(Warehouse warehouse, Long id) {
        Optional<Section> section = warehouse.getSections()
                .stream()
                .filter(s -> s.getId().equals(id)).findFirst();
        if (section.isEmpty()) {
            throw new BadRequestException("Invalid section");
        }
        return section.get();
    }


    /***
     * que o setor corresponde aos tipos de produto - presumindo que todo o lote vem com o mesmo tipo de categoria
     * @author Thaíssa Carrafa
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
     * @author Thaíssa Carrafa
     */

    private void ifTheSectionHasCapacity(Section section, InboundOrderDTO inbound) {
        Float maxCapacity = section.getMaxCapacity();
        int currentCapacity = section.getBatchStocks().size(); // ele analisa o tamanho de produtos do json
        Float availableCapacity = maxCapacity - currentCapacity;
        int neededCapacity = inbound.getBatchStock().size();
        if (availableCapacity < neededCapacity) {
            throw new BadRequestException("Section don't have enought space.");
        }
    }
}



