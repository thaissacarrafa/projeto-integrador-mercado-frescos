package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.*;
import com.meli.projetointegradormelifrescos.enums.Category;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float maxCapacity;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("section")
    private Warehouse warehouse;

    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<InboundOrder> inboundOrders;

    @OneToMany
    @JsonIgnoreProperties("section")
    private List<Batch> batches;
}
