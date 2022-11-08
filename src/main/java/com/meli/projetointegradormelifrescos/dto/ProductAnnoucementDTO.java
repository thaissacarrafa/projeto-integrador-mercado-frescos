package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.enums.ProductCategory;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductAnnoucementDTO {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser vazio! Infome um nome válido.")
    private String name;
    @NotBlank(message = "A quantidade de produtos não pode ser vazio! Infome uma quantidade válida.")
    private Double volume;
    @NotBlank(message = "O preço do produto não pode ser vazio! Infome um preço válido.")
    private BigDecimal price;
    @NotBlank(message = "A categoria não pode estar vazia. Informe uma categoria válida.")
    private ProductCategory category;
    @NotBlank(message = "A data de expiração não pode estar vazia.")
    private Date expirationDate;

}


