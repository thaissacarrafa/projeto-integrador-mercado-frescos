package com.meli.projetointegradormelifrescos.model;

import com.meli.projetointegradormelifrescos.enums.Category;
import lombok.*;
import java.math.*;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    @Max(value = 5, message = "the maximum score value is 5")
    @Min(value = 1, message = "the minimum score value is 1")
    private Double avarageEvaluation;

    @ManyToOne
    @JoinColumn(name = "seller")
    @JsonIgnoreProperties("announcement")
    @JsonBackReference
    private Seller seller;

    @OneToMany(mappedBy = "announcement")
    @JsonIgnore
    private List<Feedback> feedbacks;

}
