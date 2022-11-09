package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.enums.ProductCategory;
import com.meli.projetointegradormelifrescos.model.ProductAnnoucement;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class ProductAnnoucementDTO {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser vazio! Infome um nome válido.")
    private String name;
    @NotBlank(message = "O preço do produto não pode ser vazio! Infome um preço válido.")
    private BigDecimal price;
    @NotBlank(message = "A categoria não pode estar vazia. Informe uma categoria válida.")
    private ProductCategory category;
    @NotBlank(message = "A descrição não pode estar vazia!")
    private String description;

    public ProductAnnoucementDTO(ProductAnnoucement productAnnoucement) {
        this.id = productAnnoucement.getId();
        this.name = productAnnoucement.getName();
        this.price = productAnnoucement.getPrice();
        this.category = productAnnoucement.getCategory();
        this.description = productAnnoucement.getDescription();
    }

// método responsável por converter a lista de Produtos
    public static List<ProductAnnoucementDTO> convertListProducts(List<ProductAnnoucement> products){
        return products.stream().map(ProductAnnoucementDTO::new).collect(Collectors.toList());
    }

}



