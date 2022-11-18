package com.meli.projetointegradormelifrescos.model;

import com.meli.projetointegradormelifrescos.enums.Category;
import lombok.*;
import java.math.*;
import java.util.List;
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

    @ManyToOne
    @JoinColumn(name = "seller")
    @JsonIgnoreProperties("announcement")
    @JsonBackReference
    private Seller seller;

    @OneToMany(mappedBy = "announcement")
    @JsonManagedReference
    private List<PurchaseProduct> purchaseProducts;
}
