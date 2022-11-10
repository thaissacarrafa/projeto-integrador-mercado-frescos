package com.meli.projetointegradormelifrescos.model;

import java.time.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;
import com.meli.projetointegradormelifrescos.dto.BatchStockDTO;

import java.util.*;
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
    private List<Batch> batches;
}
