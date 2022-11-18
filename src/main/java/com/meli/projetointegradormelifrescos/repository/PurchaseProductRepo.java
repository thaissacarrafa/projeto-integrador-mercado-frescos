package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.PurchaseProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseProductRepo extends JpaRepository<PurchaseProduct, Long> {
    PurchaseProduct save(PurchaseProduct purchaseProduct);
}
