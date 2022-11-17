package com.meli.projetointegradormelifrescos.model;

import lombok.*;
import java.time.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;


@Entity
@Data
public class InboundOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate orderDate;

    @Column(nullable = false)
    private Long orderNumber;

    @OneToMany(mappedBy = "inboundOrder")
    @JsonIgnoreProperties("inboundOrder")
    @JsonManagedReference
    private List<Batch> batches;

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
