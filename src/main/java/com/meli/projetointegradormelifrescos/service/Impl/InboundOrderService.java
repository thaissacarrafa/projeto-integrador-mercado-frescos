
package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.projetointegradormelifrescos.config.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.Output.InboundOrderDTO;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.meli.projetointegradormelifrescos.model.*;
import com.meli.projetointegradormelifrescos.repository.*;
import com.meli.projetointegradormelifrescos.service.IInboundOrderService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;


@Service
public class InboundOrderService implements IInboundOrderService {

    @AutoConfigureOrder
    private InboundOrder inboundOrder;

    @Autowired
    private InboundOrderRepo inboundOrderRepo;

    @Autowired
    private WarehouseRepo warehouseRepo;

    // @Autowired
    // private SectionRepo sectionRepo;

    // @Autowired
    // private BatchRepo batchStockRepo;

    @Autowired
    private ManagerRepo managerRepo;

    // @Autowired
    // private ModelMapper modelMapper;


    @Override
    @Transactional
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

        InboundOrder inboundOrderEntity = new InboundOrder();

        inboundOrderEntity.setOrderDate(inboundOrderDTO.getOrderDate());
        inboundOrderEntity.setBatches(inboundOrderDTO.getBatchStock().stream().map(BatchDTO::toEntity).collect(Collectors.toList()));
        inboundOrderEntity.setSection(inboundOrderDTO.getSectionCode());
        inboundOrderEntity.setManager(inboundOrderDTO.getManagerId());
        inboundOrderEntity.setWarehouse(inboundOrderDTO.getWarehouseCode());

        inboundOrderRepo.save(inboundOrderEntity);

        return inboundOrderDTO;
    }
    
        // private void saveBatchStock(BatchDTO dto, InboundOrder inboundOrder) {
        //     Batch batchStock = IBatchStockMapper.MAPPER.mappingBatchStockDTOToBatchStock(dto);
        //     batchStock.setInboundOrder(inboundOrder);
        //     batchStock.setSection(inboundOrder.getSection());
        //     batchStockRepo.save(batchStock);
        // }

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
     private void sectorIsEqualsBatch(BatchDTO batch, Section section) {
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
        int currentCapacity = section.getBatches().size(); // ele analisa o tamanho de produtos do json
        Float availableCapacity = maxCapacity - currentCapacity;
        int neededCapacity = inbound.getBatchStock().size();
        if (availableCapacity < neededCapacity) {
            throw new BadRequestException("Section don't have enought space.");
        }
    }
}



