package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PurchaseProduct {
    @Id
    @Column(name = "id")
    private Long productId;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @ManyToOne
    @JoinColumn(name = "purchase_order_id", nullable = false)
    @JsonIgnoreProperties("purchaseOrders")
    private PurchaseOrder purchaseOrder;
}
