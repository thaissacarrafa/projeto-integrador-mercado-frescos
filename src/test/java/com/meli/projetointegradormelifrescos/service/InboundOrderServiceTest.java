package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.enums.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.enums.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.enums.repository.ManagerRepo;
import com.meli.projetointegradormelifrescos.enums.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void valid_if_The_Section_Has_Capacity() throws BadRequestException {
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
    }


    @Test
    @DisplayName("valida se passando uma warehouse inexistente é lançado uma exception ")
    void test_Warehouse_Exists_exception() throws NotFoundException {
        InboundOrderDTO inboundOrderDTO = this.createInboundOrderDTOMock();

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(anyLong());
        Mockito.when(warehouseRe).thenReturn(Optional.empty());

        NotFoundException except = assertThrows(NotFoundException.class, () ->  this
                .service.validWarehouse(inboundOrderDTO.getWarehouseCode()));

        assertEquals(except.getMessage(), "Invalid warehouse Code");
    }

    @Test
    @DisplayName("valida se passando uma warehouse é retornada quando  é passado um codigo existente")
    void test_Warehouse_exit_when_warehouse_Correct(){
        Warehouse warehouse = createWarehouseMock();

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(anyLong());
        Mockito.when(warehouseRe).thenReturn(Optional.of(warehouse));

        Warehouse warehouseReturned = this.service.validWarehouse(1L);

        assertEquals(warehouse.getCode(), warehouseReturned.getCode());
    }
    @Test
    @DisplayName("valida se ao passar um mannager inesistente é retornada uma exception")
        void valid_find_manager_from_warehouse_exception() throws BadRequestException {
        Manager  manager = createManagerMock();
        manager.setId(3l);
        Warehouse warehouse = this.createWarehouseMock();
        warehouse.setCode(32L);

        BadRequestException except = assertThrows(BadRequestException.class, () ->  this
                .service.findManagerFromWarehouse(warehouse, manager.getId()));
        assertEquals(except.getMessage(), "Invalid Manager Id");

    }

    @Test
    @DisplayName("valida se ao passar um mannager existente é retornado um mannager")
    void valid_find_manager_from_warehouse(){
        Manager manager = createManagerMock();
        Warehouse warehouse = createWarehouseMock();
        Mockito.when(managerRepo.findManagerById(anyLong()))
                .thenReturn(manager);
        Manager managerReturned = this.service.findManagerFromWarehouse(warehouse,manager.getId());
        assertEquals(manager.getId(), managerReturned.getId());
    }

    @Test
    @DisplayName("valida se ao passar uma section inexistente lança um exception")
    void  valid_find_Section_By_Code_exception() throws BadRequestException {
        BatchDTO bachDtoMock = CreateBatchStockDTOMock();
        bachDtoMock.setCurrentTemperature(14f);
        Section sectionMock =  createSectionMock();
        BadRequestException except = assertThrows(BadRequestException.class, () ->  this
                .service.sectorIsEqualsBatch(bachDtoMock,sectionMock));
        assertEquals(except.getMessage(), "Batch doesn't belong to the section.");
    }

    @Test
    @DisplayName("validate search by section by id")
    void  valid_find_Section_By_Code()  {
        BatchDTO bachDtoMock = CreateBatchStockDTOMock();
        bachDtoMock.setCurrentTemperature(16f);
        Section sectionMock = createSectionMock();
        assertDoesNotThrow(() ->  this.service.sectorIsEqualsBatch(bachDtoMock,sectionMock));
    }


    @Test
    @DisplayName("valida se lança uma section ao passar uma section exception")
    void valid_find_Section_exeption() throws BadRequestException{
        Warehouse warehouse = createWarehouseMock();

        BadRequestException except = assertThrows(BadRequestException.class, () ->  this
                .service.findSectionByCode(warehouse, 37L));
        assertEquals(except.getMessage(), "Invalid section");
    }

    @Test
    @DisplayName("validate search by batchStock")
    void valid_getBatchStock(){
        Warehouse warehouse = createWarehouseMock();
        Section sectionMock = createSectionMock();

        InboundOrderDTO inboundOrderDTOMock = createInboundOrderDTOMock();
        Section sectionReturned = this.service.findSectionByCode(warehouse, warehouse.getId());
        assertEquals(sectionReturned,  sectionMock);
    }

    @Test
    @DisplayName("valida se o o pedido de entrada é registrado ")
    void create_InboundOrder_Saver_When_Correct_payload(){
     /*  InboundOrderDTO inboundOrderDTOMock = createInboundOrderDTOMock();
        Warehouse warehouse = createWarehouseMock();
        warehouse.getManagers().setId(4L);
        Manager manager = createManagerMock();
        manager.setId(4L);

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(anyLong());
        Mockito.when(warehouseRe).thenReturn(Optional.of(warehouse));

        Mockito.when(this.service.findManagerFromWarehouse(warehouse, 4L)).thenReturn(manager);


        BatchDTO bachDtoMock = CreateBatchStockDTOMock();
        bachDtoMock.setCurrentTemperature(16f);
        Section sectionMock = createSectionMock();


        this.service.createInboundOrder(inboundOrderDTOMock);*/
    }



    private BatchDTO CreateBatchStockDTOMock() {
        BatchDTO requestExampleBaches = new BatchDTO();
        requestExampleBaches.setBatchNumber(1L);
        requestExampleBaches.setProductId(1L);
        requestExampleBaches.setCurrentTemperature(16f);
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
        inboundOrderEntity.setBatches(inboundOrderDTO.getBatchStock().stream().map(BatchDTO::entityToDTO).collect(Collectors.toList()));
        inboundOrderEntity.setSection(createSectionMock());
        inboundOrderEntity.setManager(createManagerMock());
        inboundOrderEntity.setOrderNumber(inboundOrderDTO.getOrderNumber());
        inboundOrderEntity.setWarehouse(createWarehouseMock());
        return inboundOrderEntity;
    }
}
