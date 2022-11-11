package com.meli.projetointegradormelifrescos.model;

import lombok.*;
import java.time.*;
import java.math.*;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Batch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long currentTemperature;

    @Column(nullable = false)
    private int productQuantity;

    @Column(nullable = false)
    private LocalDate manufacturingDate;

    @Column(nullable = false)
    private LocalDateTime manufacturingTime;

    @Column(nullable = false)
    private Float volume;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "announcement_id")
    @JsonIgnoreProperties("batches")
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "section_id")
    @JsonIgnoreProperties("batches")
    private Section section;
}
