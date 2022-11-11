package com.meli.projetointegradormelifrescos.model;

import com.meli.projetointegradormelifrescos.enums.Category;
import lombok.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

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

    @Column(nullable = false)
    private Category category;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private Warehouse warehouse;
    
    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private List<InboundOrder> inboundOrders;

    @OneToMany
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private List<BatchStock> batchStocks;
}
