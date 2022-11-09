package com.meli.projetointegradormelifrescos.model;

import java.time.LocalDate;

import javax.persistence.*;

import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class InboundOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDate orderDate;

    private Long orderNumber;
}
