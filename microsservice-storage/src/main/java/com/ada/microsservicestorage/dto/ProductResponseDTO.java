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
public class ProductResponseDTO {

    private Long id;
    private String name;
    private String category;


    public ProductEntity toEntity() {
        ModelMapper mapper = new ModelMapper();
        ProductEntity entity = mapper.map(this, ProductEntity.class);
        return entity;
    }
}
