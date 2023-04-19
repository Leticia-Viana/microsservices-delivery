package com.ada.microsservicestorage.mappers;

import com.ada.microsservicestorage.dto.ProductDTO;
import com.ada.microsservicestorage.dto.ProductResponseDTO;
import com.ada.microsservicestorage.dto.ProductStorageResponseDTO;
import com.ada.microsservicestorage.entities.ProductEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ProductMapper {

    public ProductStorageResponseDTO toDto (ProductEntity productEntity){
        if (Objects.isNull(productEntity)){
            return null;
        }
        else {
            return ProductStorageResponseDTO.builder()
                    .id(productEntity.getId())
                    .name(productEntity.getName())
                    .category(productEntity.getCategory())
                    .qtdd((productEntity.getQtdd()))
                    .build();
        }
    }

    /*public List<ProductDTO> toListDto (List<ProductEntity> listProductEntity){
        if (Objects.isNull(listProductEntity)){
            return new ArrayList<>();
        }
        else {
            return listProductEntity.stream().map(this::toDto).collect(Collectors.toList());
        }
    }*/

    public ProductEntity toEntity (ProductResponseDTO productResponseDTO){
        if (Objects.isNull(productResponseDTO)){
            return null;
        }
        else {
            return ProductEntity.builder()
                    .id(productResponseDTO.getId())
                    .name(productResponseDTO.getName())
                    .category(productResponseDTO.getCategory())
                    .build();
        }
    }
}
