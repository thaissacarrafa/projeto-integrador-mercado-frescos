package com.meli.projetointegradormelifrescos.model;

import lombok.*;
import java.util.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float temperature;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    @JsonManagedReference
    @ToString.Exclude
    private List<Announcement> announcements;

    @OneToMany(mappedBy = "category")
    @JsonIgnoreProperties("category")
    @JsonManagedReference
    @ToString.Exclude
    private List<Section> sections;
}
