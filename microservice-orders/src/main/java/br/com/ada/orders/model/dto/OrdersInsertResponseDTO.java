package br.com.ada.orders.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersInsertResponseDTO {

    private Long orderId;

    private String clientName;

    private String deliveryAddress;
}
