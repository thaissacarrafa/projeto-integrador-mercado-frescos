package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Manager;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {

    @NotBlank
    private Long warehouseCode;

    public Manager managerList;
}
