package com.meli.projetointegradormelifrescos.dto;

import com.meli.projetointegradormelifrescos.model.Batch;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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
