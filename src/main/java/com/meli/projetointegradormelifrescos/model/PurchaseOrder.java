package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
@Data
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Long buyerId;

    @Column(nullable = false)
    private String orderStatus;

    @OneToMany(mappedBy = "purchaseOrder")
    @JsonIgnoreProperties("purchaseOrder")
    @JsonManagedReference
    private List<PurchaseProduct> products;
}
