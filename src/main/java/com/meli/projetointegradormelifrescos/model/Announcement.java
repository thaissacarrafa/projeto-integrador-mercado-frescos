package com.meli.projetointegradormelifrescos.model;

import com.meli.projetointegradormelifrescos.enums.Category;
import lombok.*;
import java.math.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;

@Entity
@Data
public class Announcement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private Boolean Alert;

    @ManyToOne
    @JoinColumn(name = "seller")
    @JsonIgnoreProperties("announcement")
    @JsonBackReference
    private Seller seller;

}
