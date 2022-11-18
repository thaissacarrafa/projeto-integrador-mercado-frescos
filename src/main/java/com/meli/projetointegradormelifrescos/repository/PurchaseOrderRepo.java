package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Manager;
import com.meli.projetointegradormelifrescos.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepo extends JpaRepository<PurchaseOrder, Long> {
    PurchaseOrder save(PurchaseOrder purchaseOrder);

}
