package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Batch {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "batch_number")
    private Long batchNumber;

    @Column(name = "product_id", nullable = false)
    private Long productId;

    @Column(name = "product_quantity", nullable = false)
    private int productQuantity;

    @Column(name = "current_temperature", nullable = false)
    private Float currentTemperature;

    @Column(name = "initial_quantity", nullable = false)
    private Integer initialQuantity;

    @Column(name = "manufacturing_date", nullable = false)
    private LocalDate manufacturingDate;

    @Column(name = "manufacturing_time", nullable = false)
    private LocalDateTime manufacturingTime;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Column(name = "volume", nullable = false)
    private Float volume;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "inbound_order_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties("batchStocks")
    private Section section;

}


