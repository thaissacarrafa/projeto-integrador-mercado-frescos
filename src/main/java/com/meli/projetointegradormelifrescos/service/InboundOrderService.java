
package com.meli.projetointegradormelifrescos.service;

import java.util.*;
import java.util.stream.*;

import com.meli.projetointegradormelifrescos.dto.*;
import com.meli.projetointegradormelifrescos.exception.*;
import com.meli.projetointegradormelifrescos.model.*;
import com.meli.projetointegradormelifrescos.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.stereotype.*;

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

        InboundOrder inboundOrderEntity = InboundOrder.builder()
                .orderDate(inboundOrderDTO.getOrderDate())
                .batches(inboundOrderDTO.getBatchStock().stream().map(BatchDTO::entityToDTO).collect(Collectors.toList()))
                .section(section)
                .manager(manager)
                .orderNumber(inboundOrderDTO.getOrderNumber())
                .warehouse(warehouse)
                .id(inboundOrderDTO.getOrderNumber())
                .build();

        inboundOrderRepo.save(inboundOrderEntity);
        inboundOrderDTO.getBatchStock().forEach(b -> saveBatchStock(b, inboundOrderEntity));

        return inboundOrderDTO.getBatchStock();
    }

    @Override
    public List<BatchDTO> updateInboundOrder(Long orderId, InboundOrderDTO inboundOrderDTO) {
        var order = inboundOrderRepo.findById(orderId);

        if (order.isEmpty() || !orderId.equals(inboundOrderDTO.getOrderNumber())) {
            throw new NotFoundException("Pedido não encontrado");
        }

        return createInboundOrder(inboundOrderDTO);
    }

    /***
     *
     * método responsável por validar se a warehouse é válida, identificando
     * se ele é ou nao vazio
     *
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
     * ainda falta fazer o Manager repo ou tentar outra forma de validar se o
     * representante é daquele armazem
     * quem sabe usando stream pra mapear a lista de representantes do armazem e
     * verificar se é igual o informado.
     *
     * @param warehouse
     * @param warehouse, id
     * @author Thaíssa Carrafa, Leonardo Santos e Igor Fernandes
     ***/
    private Manager findManagerFromWarehouse(Warehouse warehouse, Long managerid) {

        if (!warehouse.getManagers().getId().equals(managerid)) {
            throw new BadRequestException("Invalid Manager Id");
        }

        return managerRepo.findManagerById(managerid);
    }

    /***
     * metodo que valida se o setor é o correto
     *
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
     * que o setor corresponde aos tipos de produto - presumindo que todo o lote vem
     * com o mesmo tipo de categoria
     *
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
     *
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

    private Batch saveBatchStock(BatchDTO dto, InboundOrder inboundOrder) {
        Batch batchBuilder = Batch.builder()
                .batchNumber(dto.getBatchNumber())
                .productId(dto.getProductId())
                .currentTemperature(dto.getCurrentTemperature())
                .manufacturingTime(dto.getManufacturingTime())
                .manufacturingDate(dto.getManufacturingDate())
                .volume(dto.getVolume())
                .dueDate(dto.getDueDate())
                .price(dto.getPrice())
                .inboundOrder(inboundOrder)
                .initialQuantity(dto.getProductQuantity())
                .productQuantity(dto.getProductQuantity())
                .section(inboundOrder.getSection())
                .build();
        batchRepo.save(batchBuilder);
        return batchBuilder;
    }

    /**
     * Busca uma ordem de entrada pelo ID
     *
     * @param id
     * @author Leonardo Santos
     */
    public InboundOrder findById(Long id) {
        return inboundOrderRepo.findById(id).orElseThrow(
                () -> new NotFoundException("Order " + id + " not found"));
    }

}
