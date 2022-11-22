package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.model.Batch;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class AnnoucementDTO {

    private Long productId;
    @NotBlank(message = "O nome do produto não pode ser vazio! Infome um nome válido.")
    private String name;
    @NotNull
    private BigDecimal price;
    @NotBlank(message = "A descrição não pode estar vazia!")
    private String description;

    @NotBlank(message = "A categoria não pode estar vazia!")
    private Category category;

    @NotNull
    private Double AverageEvaluation;

    public AnnoucementDTO(Announcement announcement){
        this.productId = announcement.getId();
        this.name = announcement.getName();
        this.price = announcement.getPrice();
        this.description = announcement.getDescription();
        this.AverageEvaluation = announcement.getAvarageEvaluation();
    }

    public AnnoucementDTO(Long id, String name, BigDecimal price, Category category, String description) {
        this.productId = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public AnnoucementDTO(Long productId, String name, BigDecimal price, Category category, Double avarageEvaluation) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.AverageEvaluation = avarageEvaluation;
    }

    public Long getId() {
        return productId;
    }
}




