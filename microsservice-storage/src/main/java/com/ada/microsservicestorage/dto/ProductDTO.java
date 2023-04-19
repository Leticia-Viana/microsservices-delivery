package com.ada.microsservicestorage.dto;

import com.ada.microsservicestorage.entities.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Long id;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @NotNull(message = "Preenchimento Obrigatório")
    @Length(min = 3, max= 80, message= "O número de caracteres deve ser entre 3 e 80")
    @Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")
    private String name;

    @NotEmpty(message = "Preenchimento Obrigatório")
    @NotNull(message = "Preenchimento Obrigatório")
    @Length(min = 3, max= 80, message= "O número de caracteres deve ser entre 3 e 80")
    @Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")
    private String category;

    private Long qtdd;


    public ProductEntity toEntity() {
        ModelMapper mapper = new ModelMapper();
        ProductEntity entity = mapper.map(this, ProductEntity.class);
        return entity;
    }
}
