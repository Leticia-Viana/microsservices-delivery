package br.com.ada.orders.model.dto;

import br.com.ada.orders.model.entity.ProductEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTORequest {

    @NotBlank(message = "Enter the customer's name, it cannot be null")
    private String clientName;

    @NotEmpty(message = "Enter the customer's address, it cannot be null")
    private String deliveryAddress;

    @NotEmpty(message = "Enter the products, it cannot be null")
    private List<ProductsRequestDTO> productsRequestDTOList;

}
