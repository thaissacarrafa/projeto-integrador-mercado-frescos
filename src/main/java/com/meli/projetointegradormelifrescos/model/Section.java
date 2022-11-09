package com.meli.projetointegradormelifrescos.model;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Data
@Entity
public class Section {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private Float maxCapacity;

    @Column(nullable = false)
    private Float temperature;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private Seller seller;
    
    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    @JsonBackReference
    private List<InboundOrder> inboundOrders;
}
