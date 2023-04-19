package com.ada.microsservicestorage.entities;

import com.ada.microsservicestorage.dto.ProductDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "STORAGE")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String category;
    private Long qtdd;

//    public ProductDTO toDto() {
//            ModelMapper mapper = new ModelMapper();
//            ProductDTO dto = mapper.map(this, ProductDTO.class);
//            return dto;
//    }

}
