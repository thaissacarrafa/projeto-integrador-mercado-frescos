package com.meli.projetointegradormelifrescos.model;

import com.meli.projetointegradormelifrescos.enums.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table (name = "Products")
public class ProductAnnoucement implements Serializable {

    public ProductAnnoucement() {
        super();
    }

    private static final long serialVersionID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private BigDecimal price;
    private String description;
    private String category;
    private String seller;

}