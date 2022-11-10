package com.meli.projetointegradormelifrescos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyerDTO {

    private Long id;
    @NotBlank(message = "O nome do comprador deve ser válido")
    private String name;
}
