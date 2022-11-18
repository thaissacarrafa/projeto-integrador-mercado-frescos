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

@Data
@NoArgsConstructor
@Getter
@Setter

public class AnnoucementDTO {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser vazio! Infome um nome válido.")
    private String name;
    @NotNull
    private BigDecimal price;
    @NotBlank(message = "A descrição não pode estar vazia!")
    private String description;

    @NotBlank(message = "A categoria não pode estar vazia!")
    private Category category;


    public AnnoucementDTO(Announcement annoucement) {
        this.id = annoucement.getId();
        this.name = annoucement.getName();
        this.price = annoucement.getPrice();
        this.description = annoucement.getDescription();
    }

    public AnnoucementDTO(Long id, String name, BigDecimal price, Category category, String description) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.description = description;
    }

    public Long getId() {
        return id;
    }
}




