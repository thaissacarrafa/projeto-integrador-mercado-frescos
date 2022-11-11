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
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

  //  @OneToMany(mappedBy = "seller")
   // @JsonIgnoreProperties("seller")
   // private List<Announcement> announcements;

    @OneToMany(mappedBy = "seller")
    @JsonIgnoreProperties("seller")
    private List<Section> sections;
}
