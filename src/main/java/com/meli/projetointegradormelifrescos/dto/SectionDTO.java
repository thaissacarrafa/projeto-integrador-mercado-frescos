package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.enums.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SectorDTO {

    private Long id;
    @NotBlank(message = "O nome do setor não pode ser vazio! Infome um nome válido.")
    private String name;
    @NotBlank(message = "A temperatura deste setor não pode ser vazio para melhor acondicionamento dos produtos! Infome uma temperatura válida.")
    private float temperature;
    @NotBlank(message = "A categoria não pode estar vazia. Informe uma categoria válida.")
    private Category category;
    @NotBlank(message = "A capacidade não pode estar vazia! Informe quantos produtos cabem neste setor.")
    private String capacity;


}
