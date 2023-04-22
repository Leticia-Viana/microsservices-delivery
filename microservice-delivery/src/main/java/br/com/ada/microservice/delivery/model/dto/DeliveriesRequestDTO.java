package br.com.ada.microservice.delivery.model.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeliveriesRequestDTO {

		
//	@NotBlank(message = "Enter the customer's name, it cannot be null")
//    private String clientName;
//
//    @NotEmpty(message = "Enter the customer's address, it cannot be null")
//    private String deliveryAddress;
//
//    @NotEmpty(message = "Enter the products, it cannot be null")
//    private List<ProductsRequestDTO> listProducts;

    private OrderRequestDto orderRequestDto;
}
