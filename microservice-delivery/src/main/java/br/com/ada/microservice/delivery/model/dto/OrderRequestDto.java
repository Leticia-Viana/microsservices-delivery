package br.com.ada.microservice.delivery.model.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderRequestDto {

    private Long orderId;
    private String clientName;
    private String deliveryAddress;

}
