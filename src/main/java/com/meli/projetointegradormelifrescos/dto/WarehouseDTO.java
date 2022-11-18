package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Manager;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    @NotBlank
    private Long warehouseCode;
    public  Manager managerList;

}