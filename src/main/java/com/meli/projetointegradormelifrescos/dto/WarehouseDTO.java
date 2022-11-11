package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Manager;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WarehouseDTO {
    @NotBlank
    private String warehouseCode;
    public List<@Valid Manager> managerList;

}