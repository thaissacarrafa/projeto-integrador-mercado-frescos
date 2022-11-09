package com.meli.projetointegradormelifrescos.model;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;


@Data
@Entity
public class Warehouse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "warehouse")
    @JsonIgnoreProperties("warehouse")
    @JsonBackReference
    private List<Section> sections;

    //@OneToOne(mappedBy = "manager_id") ??
}
