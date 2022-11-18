package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Manager;
import lombok.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WarehouseDTO {
    @NotBlank
    private Long warehouseCode;
    private Long productId;
    private int totalQuantity;
    public  Manager managerList;
    SectionDTO sectionDTO;
    List<BatchDTO> batchDTOList;



}