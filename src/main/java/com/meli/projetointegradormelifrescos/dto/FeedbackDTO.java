package com.meli.projetointegradormelifrescos.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FeedbackDTO {
    private Long feedbackId;
    private String productName;
    private String comment;

    @Max(value = 5, message = "the maximum score value is 5")
    @Min(value = 1, message = "the minimum score value is 1")
    private Double evaluation;
}
