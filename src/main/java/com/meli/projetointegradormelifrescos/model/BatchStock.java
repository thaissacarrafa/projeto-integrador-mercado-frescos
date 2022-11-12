package com.meli.projetointegradormelifrescos.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BatchStock {
    @Id
    @Column(name = "id")
    private Integer batchNumber;

    @Column(name = "product_sku", nullable = false)
    private String productId;

    @Column(name = "current_temperature", nullable = false)
    private Float currentTemperature;

    @Column(name = "min_temperature", nullable = false)
    private Float minTemperature;

    @Column(name = "max_temperature", nullable = false)
    private Float MaxTemperature;

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

    @Column(name = "alert", nullable = false)
    private boolean alert; // requisito 06 Thaíssa

    @ManyToOne
    @JoinColumn(name = "inbound_order_id", nullable = false)
    @JsonIgnoreProperties("inboundOrders")
    private InboundOrder inboundOrder;

    @ManyToOne
    @JoinColumn(name = "section_id", nullable = false)
    @JsonIgnoreProperties("batchStocks")
    private Section section;

}


