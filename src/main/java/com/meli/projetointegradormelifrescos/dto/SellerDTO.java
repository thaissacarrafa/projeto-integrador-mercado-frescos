package com.meli.projetointegradormelifrescos.dto;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {

    private int id;

    @NotBlank(message = "O nome do comprador deve ser válido")
    private String name;
}
