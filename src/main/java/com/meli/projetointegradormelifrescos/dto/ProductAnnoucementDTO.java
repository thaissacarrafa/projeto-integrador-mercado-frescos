package com.meli.projetointegradormelifrescos.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    private Long productId;
    @NotNull(message = "O nome do produto não pode estar vazio")
    private String name;
    @NotNull(message = "O volume do produto não pode estar vazio")
    private Double volume;
    @NotNull(message = "A preço não pode estar vazio")
    @DecimalMin("0.00")
    private BigDecimal price;
    @NotNull(message = "A categoria não pode estar vazia")
    private ProductCategory category;
    @NotNull(message = "A avaliaçao do produto nao pode estar vazia")
    private Double AverageRating;

}


- id: Long
        - name:  String
        - price: BigDecimal
        - description: String
        - sellerId: Long
        - category: String
        - expirationDate: date
