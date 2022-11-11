package com.meli.projetointegradormelifrescos.model;

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
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonIgnoreProperties("announcement")
    @JsonBackReference
    private Category category;

    @ManyToOne
    @JoinColumn(name = "saller_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"batches", "storage"})
    private Section section;
}
