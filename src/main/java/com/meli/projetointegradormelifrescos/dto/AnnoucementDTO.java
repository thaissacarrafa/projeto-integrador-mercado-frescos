package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.sun.istack.NotNull;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class AnnoucementDTO {

    private Long id;
    @NotBlank(message = "O nome do produto não pode ser vazio! Infome um nome válido.")
    private String name;
    @NotNull
    private BigDecimal price;
    @NotBlank(message = "A descrição não pode estar vazia!")
    private String description;

    @NotBlank(message = "A descrição não pode estar vazia!")
    private Category category;

    public AnnoucementDTO(Announcement annoucement) {
        this.id = annoucement.getId();
        this.name = annoucement.getName();
        this.price = annoucement.getPrice();
        this.category = annoucement.getCategory();
        this.description = annoucement.getDescription();
    }

// método responsável por converter a lista de Produtos
    public static List<AnnoucementDTO> convertListProducts(List<Announcement> products){
        return products.stream().map(AnnoucementDTO::new).collect(Collectors.toList());
    }

}



