package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long code;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    private List<Section> sections;

    @OneToOne
    @JoinColumn(name = "manager_id")
    @JsonIgnoreProperties("manager")
    private Manager managers;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    private List<InboundOrder> inboundOrders;
}
