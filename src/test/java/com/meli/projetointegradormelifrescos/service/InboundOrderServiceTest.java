package com.meli.projetointegradormelifrescos.service;

import static com.meli.projetointegradormelifrescos.utils.GenerateMocksPayloads.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.exception.BadRequestException;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.InboundOrder;
import com.meli.projetointegradormelifrescos.model.Manager;
import com.meli.projetointegradormelifrescos.model.Section;
import com.meli.projetointegradormelifrescos.model.Warehouse;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.repository.ManagerRepo;
import com.meli.projetointegradormelifrescos.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.utils.GenerateMocksPayloads;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class InboundOrderServiceTest {

    /***
     *   @message Implementacao de testes
     *   @author Igor Fernandes
     */

    @Mock
    private InboundOrderRepo inboundOrderRepo;

    @Mock
    private WarehouseRepo warehouseRepo;

    @Mock
    private BatchRepo batchRepo;

    @Mock
    private ManagerRepo managerRepo;

    @InjectMocks
    @Spy
    private InboundOrderService service;

    @Test
    @DisplayName(
        "testa se uma exeção é lançada ao um volume maior que a capacidade da seção"
    )
    void valid_if_The_Section_Has_Capacity() throws BadRequestException {
        InboundOrderDTO inboundOrderDTO = createInboundOrderDTOMock();
        Section section = createSectionMock();
        BatchDTO batch = CreateBatchStockDTOMock();
        BatchDTO batch2 = CreateBatchStockDTOMock();

        batch2.setVolume(10f);
        batch2.setBatchNumber(2l);
        batch2.setCurrentTemperature(18F);

        section.setMaxCapacity(1f);
        section.setCategory(Category.FRESCO);
        section.setId(3L);

        inboundOrderDTO.setBatchStock(Arrays.asList(batch, batch2));

        BadRequestException except = assertThrows(
            BadRequestException.class,
            () -> this.service.ifTheSectionHasCapacity(section, inboundOrderDTO)
        );
        assertEquals(except.getMessage(), "Section don't have enought space.");
    }

    @Test
    @DisplayName(
        "valida se passando uma warehouse inexistente é lançado uma exception "
    )
    void test_Warehouse_Exists_exception() throws NotFoundException {
        InboundOrderDTO inboundOrderDTO = createInboundOrderDTOMock();

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(
            anyLong()
        );
        Mockito.when(warehouseRe).thenReturn(Optional.empty());

        NotFoundException except = assertThrows(
            NotFoundException.class,
            () ->
                this.service.validWarehouse(inboundOrderDTO.getWarehouseCode())
        );

        assertEquals(except.getMessage(), "Invalid warehouse Code");
    }

    @Test
    @DisplayName(
        "valida se passando uma warehouse é retornada quando  é passado um codigo existente"
    )
    void test_Warehouse_exit_when_warehouse_Correct() {
        Warehouse warehouse = createWarehouseMock();

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(
            anyLong()
        );
        Mockito.when(warehouseRe).thenReturn(Optional.of(warehouse));

        Warehouse warehouseReturned = this.service.validWarehouse(1L);

        assertEquals(warehouse.getCode(), warehouseReturned.getCode());
    }

    @Test
    @DisplayName(
        "valida se ao passar um mannager inesistente é retornada uma exception"
    )
    void valid_find_manager_from_warehouse_exception()
        throws BadRequestException {
        Manager manager = createManagerMock();
        manager.setId(3l);
        Warehouse warehouse = createWarehouseMock();
        warehouse.setCode(32L);

        BadRequestException except = assertThrows(
            BadRequestException.class,
            () ->
                this.service.findManagerFromWarehouse(
                        warehouse,
                        manager.getId()
                    )
        );
        assertEquals(except.getMessage(), "Invalid Manager Id");
    }

    @Test
    @DisplayName(
        "valida se ao passar um mannager existente é retornado um mannager"
    )
    void valid_find_manager_from_warehouse() {
        Manager manager = createManagerMock();
        Warehouse warehouse = createWarehouseMock();
        Mockito
            .when(managerRepo.findManagerById(anyLong()))
            .thenReturn(manager);
        Manager managerReturned =
            this.service.findManagerFromWarehouse(warehouse, manager.getId());
        assertEquals(manager.getId(), managerReturned.getId());
    }

    @Test
    @DisplayName(
        "valida se ao passar uma section inexistente lança um exception"
    )
    void valid_find_Section_By_Code_exception() throws BadRequestException {
        BatchDTO bachDtoMock = CreateBatchStockDTOMock();
        bachDtoMock.setCurrentTemperature(14f);
        Section sectionMock = createSectionMock();
        BadRequestException except = assertThrows(
            BadRequestException.class,
            () -> this.service.sectorIsEqualsBatch(bachDtoMock, sectionMock)
        );
        assertEquals(
            except.getMessage(),
            "Batch doesn't belong to the section."
        );
    }

    @Test
    @DisplayName("validate search by section by id")
    void valid_find_Section_By_Code() {
        BatchDTO bachDtoMock = CreateBatchStockDTOMock();
        bachDtoMock.setCurrentTemperature(16f);
        Section sectionMock = createSectionMock();
        assertDoesNotThrow(() ->
            this.service.sectorIsEqualsBatch(bachDtoMock, sectionMock)
        );
    }

    @Test
    @DisplayName("valida se lança uma section ao passar uma section exception")
    void valid_find_Section_exeption() throws BadRequestException {
        Warehouse warehouse = createWarehouseMock();

        BadRequestException except = assertThrows(
            BadRequestException.class,
            () -> this.service.findSectionByCode(warehouse, 37L)
        );
        assertEquals(except.getMessage(), "Invalid section");
    }

    @Test
    @DisplayName("validate search by batchStock")
    void valid_getBatchStock() {
        Warehouse warehouse = createWarehouseMock();
        Section sectionMock = createSectionMock();

        InboundOrderDTO inboundOrderDTOMock = createInboundOrderDTOMock();
        Section sectionReturned =
            this.service.findSectionByCode(warehouse, warehouse.getId());
        assertEquals(sectionReturned, sectionMock);
    }

    @Test
    @DisplayName("valida se o o pedido de entrada é registrado ")
    void create_InboundOrder_Saver_When_Correct_payload() {
        InboundOrderDTO inboundOrderDTOMock = createInboundOrderDTOMock();
        inboundOrderDTOMock.setManagerId(4L);
        inboundOrderDTOMock.setSectionCode(1L);
        Warehouse warehouse = createWarehouseMock();

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(
            anyLong()
        );
        Mockito.when(warehouseRe).thenReturn(Optional.of(warehouse));

        List<BatchDTO> ProductReturSave =
            this.service.createInboundOrder(inboundOrderDTOMock);

        assertEquals(ProductReturSave, inboundOrderDTOMock.getBatchStock());
    }

    @Test
    @DisplayName(
        "valida se passando um orderNumber inexistente lença uma exception"
    )
    void update_InboundOrder_exception() throws NotFoundException {
        InboundOrderDTO inboundOrderDTOMock = createInboundOrderDTOMock();
        Optional<InboundOrder> inboundReturn = inboundOrderRepo.findById(
            inboundOrderDTOMock.getOrderNumber()
        );

        Mockito.when(inboundReturn).thenReturn(Optional.empty());
        NotFoundException except = assertThrows(
            NotFoundException.class,
            () ->
                this.service.updateInboundOrder(
                        inboundOrderDTOMock.getOrderNumber(),
                        inboundOrderDTOMock
                    )
        );
        assertEquals(except.getMessage(), "Pedido não encontrado");
    }

    @Test
    @DisplayName("valida se passando um orderNumber faz o update no banco")
    void update_InboundOrder() {
        InboundOrderDTO inboundOrderDTOMock = createInboundOrderDTOMock();
        inboundOrderDTOMock.setManagerId(4L);
        inboundOrderDTOMock.setSectionCode(1L);
        Warehouse warehouse = createWarehouseMock();

        Optional<Warehouse> warehouseRe = warehouseRepo.findWarehouseByCode(
            anyLong()
        );
        Mockito.when(warehouseRe).thenReturn(Optional.of(warehouse));

        Mockito
            .when(
                inboundOrderRepo.findById(inboundOrderDTOMock.getOrderNumber())
            )
            .thenReturn(
                Optional.of(
                    GenerateMocksPayloads.CreateInboundOrderEntityMock(
                        inboundOrderDTOMock
                    )
                )
            );
        List<BatchDTO> ProductReturnUpdateMock =
            this.service.updateInboundOrder(
                    inboundOrderDTOMock.getOrderNumber(),
                    inboundOrderDTOMock
                );

        assertEquals(
            ProductReturnUpdateMock,
            inboundOrderDTOMock.getBatchStock()
        );
    }
}
