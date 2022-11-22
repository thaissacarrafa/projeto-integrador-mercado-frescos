package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.repository.ManagerRepo;
import com.meli.projetointegradormelifrescos.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.model.Manager;
import com.meli.projetointegradormelifrescos.model.Section;
import com.meli.projetointegradormelifrescos.model.Warehouse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;



@ExtendWith(MockitoExtension.class)
public class InboundOrderServiceTest {

    @Mock private InboundOrderRepo inboundOrderRepo;
    @Mock private WarehouseRepo warehouseRepo;
    @Mock private BatchRepo batchRepo;
    @Mock private ManagerRepo managerRepo;

    @InjectMocks private InboundOrderService service;


    @Test
    @DisplayName("validate search by wareHouse")
    void valid_WareHouse_test() throws NotFoundException {

    }

    @Test
    @DisplayName("validate search by managers da warehouse")
    void valid_find_manager_from_warehouse() {

    }

    @Test
    @DisplayName("validate search by section by id")
    void  valid_find_Section_By_Code(){

    }

    @Test
    @DisplayName("validate search by batchStock")
    void valid_getBatchStock(){

    }

    @Test
    @DisplayName("validate if sections has capacity")
    void valid_if_The_Section_Has_Capacity(){

    }


    private BatchDTO CreateBatchStockDTOMock() {
        BatchDTO requestExampleBaches = new BatchDTO();
        requestExampleBaches.setBatchNumber(null);
        requestExampleBaches.setProductId(1L);
        requestExampleBaches.setCurrentTemperature(14f);
        requestExampleBaches.setProductQuantity(10);
        requestExampleBaches.setManufacturingDate(LocalDate.from(LocalDateTime.parse("2022-11-11T16:31:02")));
        requestExampleBaches.setManufacturingTime(LocalDateTime.parse("2022-11-11T16:31:02"));
        requestExampleBaches.setVolume(10f);
        requestExampleBaches.setDueDate(LocalDate.ofEpochDay(2022-11-11));
        requestExampleBaches.setPrice(BigDecimal.valueOf(79));
        return requestExampleBaches;
    }

    private InboundOrderDTO createInboundOrderDTOMock(){
        List<BatchDTO> batch = new ArrayList<BatchDTO>();
        batch.add(CreateBatchStockDTOMock());
        batch.add(CreateBatchStockDTOMock());

        InboundOrderDTO requestExample = new InboundOrderDTO();
        requestExample.setWarehouseCode(1L);
        requestExample.setOrderDate(LocalDate.parse("2022-11-11"));
        requestExample.setOrderNumber(13L);
        requestExample.setSectionCode(14L);
        requestExample.setBatchStock(batch);
        return requestExample;
    }

    private Section createSectionMock(){
        return Section.builder()
                .id(1L)
                .maxCapacity(50.0f)
                .category(Category.FRESCO)
                .warehouse(createWarehouseMock())
                .build();
    }

    private Manager createManagerMock(){
        Manager manager = new Manager();
        manager.setId(1L);
        manager.setWarehouse(createWarehouseMock());
        return manager;
    }

    private Warehouse createWarehouseMock(){
        List<InboundOrder> InBoundList = new ArrayList<InboundOrder>();
        InBoundList.add(CreateInboundOrderEntityMock(createInboundOrderDTOMock()));
        InBoundList.add(CreateInboundOrderEntityMock(createInboundOrderDTOMock()));

        return Warehouse
                .builder()
                .id(1L)
                .code(1L)
                .name("FreshHouse")
                .managers(createManagerMock())
                .inboundOrders(InBoundList).
                build();
    }

    private InboundOrder CreateInboundOrderEntityMock(InboundOrderDTO inboundOrderDTO){
        InboundOrder inboundOrderEntity = new InboundOrder();

        inboundOrderEntity.setOrderDate(inboundOrderDTO.getOrderDate());
        inboundOrderEntity.setBatches(inboundOrderDTO.getBatchStock().stream().map(BatchDTO::toEntity).collect(Collectors.toList()));
        inboundOrderEntity.setSection(createSectionMock());
        inboundOrderEntity.setManager(createManagerMock());
        inboundOrderEntity.setOrderNumber(inboundOrderDTO.getOrderNumber());
        inboundOrderEntity.setWarehouse(createWarehouseMock());

        return inboundOrderEntity;
    }
}
