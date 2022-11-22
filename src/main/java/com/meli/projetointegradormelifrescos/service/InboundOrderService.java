package com.meli.projetointegradormelifrescos.service;

import java.util.*;
import java.util.stream.*;

import com.meli.projetointegradormelifrescos.dto.*;
import com.meli.projetointegradormelifrescos.enums.Sorting;
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

    @Autowired
    private SectionRepo sectionRepo;

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
    public Warehouse validWarehouse(Long warehouseCode) {
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
     * verifica a data de validade dos produtos de um lote.
     * @author Amanda Lobo
     * @param batchList -> List<Batch>
     * @param productId -> Long
     */
    private static void verifyBatchDueDate(List<Batch> batchList, Long productId) {
        for (Batch batch : batchList) {
            AnnoucementService.verifyProductDueDate(batch, productId);
        }
    }

    /**
     * ordena uma lista de lotes pela data de validade dos produtos no lote.
     * @author Amanda Lobo
     * @param batches -> List<BatchDTO>
     * @return List<BatchDTO> - lista de lotes ordenados pela data de validade.
     */
    public static List<BatchDTO> sortByDueDate(List<BatchDTO> batches) {
        return batches.stream().sorted(Comparator.comparing(BatchDTO::getDueDate))
                .collect(Collectors.toList());
    }

    /**
     * ordenação de uma lista de lotes por lote.
     * @author Amanda Lobo
     * @param batches -> List<BatchDTO>
     * @return List<BatchDTO> -> lista de lotes ordenados por lote.
     */
    public static List<BatchDTO> sortByBatch(List<BatchDTO> batches) {
        return batches.stream().sorted(Comparator.comparing(BatchDTO::getBatchNumber))
                .collect(Collectors.toList());
    }

    /**
     * ordenação de uma lista de lotes pela quantidade de produtos contiddos no lote.
     * @author Amanda Lobo
     * @param batches -> List<BatchDTO>
     * @return List<BatchDTO> - lista de lotes ordenados pela quantidade de produtos contidos no lote.
     */
    public static List<BatchDTO> sortByQuantity(List<BatchDTO> batches) {
        return batches.stream().sorted(Comparator.comparing(BatchDTO::getProductQuantity))
                .collect(Collectors.toList());
    }

    /**
     * filtrando lotes que contem um determinado produto
     * @author Amanda Lobo
     * @param productId -> Long
     * @param inboundOrder -> InboundOrder
     * @return List<Batch> -> lista de lotes filtrados por produto
     */
    private static List<Batch> getBatchByProductId(InboundOrder inboundOrder, Long productId){
        return inboundOrder.getBatches().stream()
                .filter(batchProduct -> batchProduct.getProductId().equals(productId))
                .collect(Collectors.toList());
    }

    /**
     * cria uma Lista de Lotes
     * @author Amanda Lobo
     * @param batchList -> List<Batch>
     * @return List<BatchDTO> - Retorna uma Lista de Lotes.
     */
    private static List<BatchDTO> getBatchDTO(List<Batch> batchList) {
        return batchList.stream().map(batch -> BatchDTO.builder()
                        .batchNumber(batch.getBatchNumber())
                        .productQuantity(batch.getProductQuantity())
                        .dueDate(batch.getDueDate())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<InboundOrder> getAll() {
        return inboundOrderRepo.findAll();
    }

    /**
     * lista de produtos com todos os lotes que possui o produto(incompleto)
     * @author Amanda Lobo
     * @param productId -> Long
     * @return List<WarehouseDTO> -> retorna uma lista do tipo WarehouseDTO
     * @throws Exception
     */
    @Override
    public List<WarehouseDTO> getAllProductWarehouse(Long productId) throws Exception {
        List<InboundOrder> inboundOrderList = getAll();
        List<Batch> batchList;
        List<WarehouseDTO> warehouseDTOList = new ArrayList<>();

        for (InboundOrder inbound : inboundOrderList) {
            batchList = getBatchByProductId(inbound, productId); //242
            verifyBatchDueDate(batchList, productId);

            List<BatchDTO> batchDTOList = getBatchDTO(batchList);
        }
        return warehouseDTOList;
    }

    /**
     * ordenação de lotes por parâmetro (L= lote, Q= quantidade, V = validade).
     * @author Amanda Lobo
     * @param warehouseDTOList -> List<WarehouseDTO>
     * @param sorting -> String
     * @return List<warehouseDTO> -> retorna o pârametro inserido de forma ordenada
     */

    @Override
    public List<WarehouseDTO> getAllOrdinancesFotBatches(List<WarehouseDTO> warehouseDTOList, String sorting) {
        Sorting enumSorting = Sorting.sortingEnum(sorting);
        List<BatchDTO> batchDTOS;

        for(WarehouseDTO warehouseDTO : warehouseDTOList){
            if (enumSorting.equals(Sorting.L)){
                batchDTOS = sortByBatch(warehouseDTO.getBatchDTOList());
            } else if (enumSorting.equals(Sorting.Q)){
                batchDTOS = sortByQuantity(warehouseDTO.getBatchDTOList());
            } else {
                batchDTOS = sortByDueDate(warehouseDTO.getBatchDTOList());
            }
            warehouseDTO.setBatchDTOList(batchDTOS);
        }
        return warehouseDTOList;
    }
    /**
     * busca se um setor é o dono do lote
     * @author Amanda Lobo
     * @param sectionId -> Long
     * @param batchId -> Long
     * @exception NotFoundException
     */
    public void findBatchBySectionId(Long sectionId, Long batchId) {
        List<Batch> batchList = sectionRepo.findBatchBySectionId(sectionId, batchId);
        if (batchList.isEmpty()){
            throw new NotFoundException("batch doesn't existing in section");
        }
    }
}
