package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.*;
import java.util.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    @JsonManagedReference
    private List<Announcement> announcements;
}
