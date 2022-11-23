package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("manager")
    private Warehouse warehouse;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("manager")
    private Section section;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "inbound_order_id")
    @JsonIgnoreProperties("manager")
    private InboundOrder inboundOrder;
}
