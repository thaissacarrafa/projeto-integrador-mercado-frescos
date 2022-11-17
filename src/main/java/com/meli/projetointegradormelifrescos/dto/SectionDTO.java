package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SectionDTO {

    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private float temperature;
    @NotNull
    private Category category;
    @NotNull
    private Float capacity;
    @NotNull
    private Float currentCapacity;
    @NotNull
    private Long warehouseCode;


}
