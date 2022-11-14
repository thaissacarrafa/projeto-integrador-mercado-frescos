package com.meli.projetointegradormelifrescos.model;

import java.time.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;

import java.util.*;

import com.sun.istack.NotNull;
import lombok.*;


@Entity
@Data
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private LocalDate orderDate;

    @OneToMany(mappedBy = "inboundOrder")
    @JsonIgnoreProperties("inboundOrder")
    @JsonManagedReference
    private List<BatchStock> batchStocks;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    public Section section;


    @OneToOne
    @JoinColumn(name = "manager_id", nullable = false)
    private Manager manager;


    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    private Warehouse warehouse;
}

