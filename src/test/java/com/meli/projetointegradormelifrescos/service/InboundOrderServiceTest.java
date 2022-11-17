package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.config.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.dto.*;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.enums.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.enums.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.enums.repository.ManagerRepo;
import com.meli.projetointegradormelifrescos.enums.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.model.*;
import com.meli.projetointegradormelifrescos.service.Impl.InboundOrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import org.mockito.Mockito.*;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@ExtendWith(MockitoExtension.class)
public class InboundOrderServiceTest {

    @Mock private InboundOrderRepo inboundOrderRepo;
    @Mock private WarehouseRepo warehouseRepo;
    @Mock private BatchRepo batchRepo;
    @Mock private ManagerRepo managerRepo;

    @InjectMocks @Spy
    private InboundOrderService service;


    @Test
    @DisplayName("testa se uma exeção é lançada ao um volume maior que a capacidade da seção")
    void methods_validations_Json() throws BadRequestException {
        InboundOrderDTO inboundOrderDTO = this.createInboundOrderDTOMock();
        Section section = this.createSectionMock();
        BatchDTO batch = this.CreateBatchStockDTOMock();
        BatchDTO batch2 = this.CreateBatchStockDTOMock();

        batch2.setVolume(10f);
        batch2.setBatchNumber(2l);
        batch2.setCurrentTemperature(18F);

        section.setMaxCapacity(1f);
        section.setCategory(Category.FRESCO);
        section.setId(3L);
        section.setId(14L);

        inboundOrderDTO.setBatchStock(Arrays.asList(batch, batch2));

        BadRequestException except = assertThrows(BadRequestException.class, () ->  this
                .service.ifTheSectionHasCapacity(section, inboundOrderDTO));
        assertEquals( except.getMessage(), "Section don't have enought space.");


     //   Manager managerReMock = managerRepo.findManagerById(anyLong());
      //  when(managerReMock).thenReturn(manager);

      //  Manager managerVerifyMock = this.service.findManagerFromWarehouse(warehouse, warehouse.getManagers().getId());
      //  when(managerVerifyMock).thenReturn(manager);

       // when(this.service.findSectionByCode(warehouse, inboundOrderDTO.getSectionCode())).thenReturn(section);



     /* Optional<Warehouse> warehouseExists = this.warehouseRepo.findWarehouseByCode(12L);
        Manager manager = findManagerFromWarehouse(warehouse, inboundOrderDTO.getManagerId());
        Section section = findSectionByCode(warehouse, inboundOrderDTO.getSectionCode());
        ifTheSectionHasCapacity(section, inboundOrderDTO);
       */
    }


    @Test
    @DisplayName("valida se a warehouse passado no json existe no banco ")
    void testWarehouseExists() throws NotFoundException {
      /*  InboundOrderDTO inboundOrderDTO = this.createInboundOrderDTOMock();
        Warehouse warehouse = this.createWarehouseMock();
        warehouse.setCode(32L);

          Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(inboundOrderDTO.getWarehouseCode());
          when(warehouseRe).thenReturn(Optional.of(warehouse));

        NotFoundException except = assertThrows(NotFoundException.class, () ->  this
                .service.validWarehouse(inboundOrderDTO.getWarehouseCode()));
        assertEquals(except.getMessage(), "Section don't have enought space.");*/
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
        requestExampleBaches.setBatchNumber(1L);
        requestExampleBaches.setProductId(1L);
        requestExampleBaches.setCurrentTemperature(14f);
        requestExampleBaches.setProductQuantity(10);
        requestExampleBaches.setManufacturingDate(LocalDate.parse("2022-10-01"));
        requestExampleBaches.setManufacturingTime(LocalDateTime.parse("2022-01-01T00:00:00"));
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
        Section section = new Section();
        section.setId(1L);
        section.setCategory(Category.FRESCO);
        section.setName("test");
        section.setInboundOrders(new ArrayList<InboundOrder>());
        section.setId(1L);
        section.setMaxCapacity(100f);
        section.setBatches(new ArrayList<Batch>());
        section.setWarehouse(new Warehouse());
        return section;
    }

    private Manager createManagerMock(){
        Manager manager = new Manager();
        manager.setId(4L);
        manager.setName("Joao");
        manager.setSection(createSectionMock());
        manager.setWarehouse(new Warehouse());
        manager.setInboundOrder(new InboundOrder());
        return manager;
    }

   private Warehouse createWarehouseMock(){
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
