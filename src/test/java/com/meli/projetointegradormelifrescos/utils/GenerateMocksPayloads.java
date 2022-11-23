package com.meli.projetointegradormelifrescos.utils;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GenerateMocksPayloads {

    public static BatchDTO CreateBatchStockDTOMock() {
        BatchDTO requestExampleBaches = new BatchDTO();
        requestExampleBaches.setBatchNumber(1L);
        requestExampleBaches.setProductId(1L);
        requestExampleBaches.setCurrentTemperature(16f);
        requestExampleBaches.setProductQuantity(10);
        requestExampleBaches.setManufacturingDate(
            LocalDate.parse("2022-10-01")
        );
        requestExampleBaches.setManufacturingTime(
            LocalDateTime.parse("2022-01-01T00:00:00")
        );
        requestExampleBaches.setVolume(10f);
        requestExampleBaches.setDueDate(LocalDate.ofEpochDay(2022 - 11 - 11));
        requestExampleBaches.setPrice(BigDecimal.valueOf(79));
        return requestExampleBaches;
    }

    public static InboundOrderDTO createInboundOrderDTOMock() {
        List<BatchDTO> batch = new ArrayList<BatchDTO>();
        batch.add(CreateBatchStockDTOMock());
        batch.add(CreateBatchStockDTOMock());

        InboundOrderDTO requestExample = new InboundOrderDTO();
        requestExample.setWarehouseCode(1L);
        requestExample.setOrderDate(LocalDate.parse("2022-11-11"));
        requestExample.setOrderNumber(13L);
        requestExample.setSectionCode(14L);
        requestExample.setBatchStock(batch);
        requestExample.setManagerId(4L);
        return requestExample;
    }

    public static Section createSectionMock() {
        Section section = new Section();
        section.setId(1L);
        section.setCategory(Category.FRESCO);
        section.setName("test");
        section.setInboundOrders(new ArrayList<InboundOrder>());
        section.setMaxCapacity(100f);
        section.setBatches(new ArrayList<Batch>());
        section.setWarehouse(new Warehouse());
        return section;
    }

    public static Manager createManagerMock() {
        Manager manager = new Manager();
        manager.setId(4L);
        manager.setName("Joao");
        manager.setSection(createSectionMock());
        manager.setWarehouse(new Warehouse());
        manager.setInboundOrder(new InboundOrder());
        return manager;
    }

    public static Warehouse createWarehouseMock() {
        Section section = createSectionMock();
        Section section2 = createSectionMock();

        Warehouse warehouse = new Warehouse();
        warehouse.setManagers(createManagerMock());
        warehouse.setName("Warehouse");
        warehouse.setCode(1L);
        warehouse.setId(1L);
        warehouse.setInboundOrders(new ArrayList<InboundOrder>());
        warehouse.setSections(Arrays.asList(section, section2));

        return warehouse;
    }

    public static InboundOrder CreateInboundOrderEntityMock(
        InboundOrderDTO inboundOrderDTO
    ) {
        InboundOrder inboundOrderEntity = new InboundOrder();
        inboundOrderEntity.setOrderDate(inboundOrderDTO.getOrderDate());
        inboundOrderEntity.setBatches(
            inboundOrderDTO
                .getBatchStock()
                .stream()
                .map(BatchDTO::entityToDTO)
                .collect(Collectors.toList())
        );
        inboundOrderEntity.setSection(createSectionMock());
        inboundOrderEntity.setManager(createManagerMock());
        inboundOrderEntity.setOrderNumber(inboundOrderDTO.getOrderNumber());
        inboundOrderEntity.setWarehouse(createWarehouseMock());
        return inboundOrderEntity;
    }
}
