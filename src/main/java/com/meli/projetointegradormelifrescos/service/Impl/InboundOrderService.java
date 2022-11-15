
package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.projetointegradormelifrescos.config.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


     @Autowired
     private BatchRepo batchRepo;

    @Autowired
    private ManagerRepo managerRepo;



    @Override

    // @Transactional
    public List<BatchDTO> createInboundOrder(InboundOrderDTO inboundOrderDTO) {

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
        inboundOrderEntity.setSection(section);
        inboundOrderEntity.setManager(manager);
        inboundOrderEntity.setOrderNumber(inboundOrderDTO.getOrderNumber());
        inboundOrderEntity.setWarehouse(warehouse);

        inboundOrderRepo.save(inboundOrderEntity);
        inboundOrderDTO.getBatchStock().forEach(b -> saveBatchStock(b, inboundOrderEntity));
        return inboundOrderDTO.getBatchStock();
    }

    private Batch saveBatchStock(BatchDTO dto, InboundOrder inboundOrder) {
        Batch batchStock = new Batch();
         batchStock.setBatchNumber(dto.getBatchNumber());
         batchStock.setProductId(dto.getProductId());
         batchStock.setCurrentTemperature(dto.getCurrentTemperature());
         batchStock.setManufacturingTime(dto.getManufacturingTime());
         batchStock.setManufacturingDate(dto.getManufacturingDate());
         batchStock.setVolume(dto.getVolume());
         batchStock.setDueDate(dto.getDueDate());
         batchStock.setPrice(dto.getPrice());
         batchStock.setInboundOrder(inboundOrder);
         batchStock.setInitialQuantity(dto.getProductQuantity());
         batchStock.setProductQuantity(dto.getProductQuantity());
         batchStock.setSection(inboundOrder.getSection());
         batchRepo.save(batchStock);
         return batchStock;
     }


    /***
     *
     *  método responsável por validar se a warehouse é válida, identificando
     *  se ele é ou nao vazio
     * @author Thaíssa Carrafa, Leonardo Santos e Igor Fernandes
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
     @author Thaíssa Carrafa, Leonardo Santos e Igor Fernandes
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
     * @author Thaíssa Carrafa, Leonardo Santos e Igor Fernandes
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
     * @author Thaíssa Carrafa, Leonardo Santos e Igor Fernandes
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
     * @author Thaíssa Carrafa, Leonardo Santos e Igor Fernandes
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



