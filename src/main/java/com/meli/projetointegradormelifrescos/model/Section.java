package com.meli.projetointegradormelifrescos.model;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Getter
@Setter
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
    private Float temperature;
    
    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("section")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "warehouse_id")
    @JsonIgnoreProperties("section")
    private Warehouse warehouse;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    @JsonIgnoreProperties("section")
    private Seller seller;
    
    @OneToMany(mappedBy = "section")
    @JsonIgnoreProperties("section")
    private List<InboundOrder> inboundOrders;
}
