package com.ada.microsservicestorage.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductAddRequestDTO {
    @NotEmpty(message = "Preenchimento Obrigatório")
    @NotNull(message = "Preenchimento Obrigatório")
    private Long idProduct;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @NotNull(message = "Preenchimento Obrigatório")
    private Long qtdd;
}
