package com.meli.projetointegradormelifrescos.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchNearDueDateDTO {

    @NotNull
    private Long batchNumber;

    @NotNull
    private Long productId;

    @NotNull
    private String ProductTypedId;

    @NotNull
    private int productQuantity;

    @NotNull
    private LocalDate dueDate;
}
