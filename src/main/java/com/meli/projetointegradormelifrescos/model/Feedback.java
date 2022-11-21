package com.meli.projetointegradormelifrescos.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String comment;

    @Max(value = 5, message = "the maximum score value is 5")
    @Min(value = 1, message = "the minimum score value is 1")
    private Double evaluation;

    @ManyToOne
    @JoinColumn(name = "announcement_id", referencedColumnName = "id")
    private Announcement announcement;
}
