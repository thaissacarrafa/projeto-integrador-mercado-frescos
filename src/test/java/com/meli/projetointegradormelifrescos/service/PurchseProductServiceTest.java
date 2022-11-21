package com.meli.projetointegradormelifrescos.service;


import com.meli.projetointegradormelifrescos.repository.AnnoucementRepo;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import com.meli.projetointegradormelifrescos.repository.InboundOrderRepo;
import com.meli.projetointegradormelifrescos.model.PurchaseOrder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
public class PurchseProductServiceTest {

    @Mock private PurchaseOrder purchaseOrder;

    @Mock private BatchRepo.PurchaseOrderRepo purchaseOrderRepo;

    @Mock private InboundOrderRepo.PurchaseProductRepo purchaseProductRepo;

    @Mock private AnnoucementRepo annoucementRepo;


    @Test
    @DisplayName("Purchase")
    void create_Purchase_Order() {
        //create_Purchase_Order(PurchaseOrderDTO purchaseOrderDTO)
    }


    @Test
    @DisplayName("Purchase")
    void convert_Purchase_Product() {
        //convertPurchaseProduct(PurchaseProductDTO dto, PurchaseOrder purchaseOrder)
    }
    @Test
    @DisplayName("Purchase")
    void get_Purchase_Order() {
    //getPurchaseOrder(Long id)
    }
    @Test
    @DisplayName("Purchase")
    void put_Purchase_Order() {
       // putPurchaseOrder(Long id)
    }

}
