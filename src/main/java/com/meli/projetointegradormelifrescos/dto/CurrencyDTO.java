package com.meli.projetointegradormelifrescos.dto;

import java.math.BigDecimal;

import lombok.*;

@Data
public class CurrencyDTO {
    private String code;
    private String codein;
    private String name;
    private BigDecimal high;
    private BigDecimal low;
    private BigDecimal varBid;
    private BigDecimal pctChange;
    private BigDecimal bid;
    private BigDecimal ask;
    private String timestamp;
    private String createDate;
}
