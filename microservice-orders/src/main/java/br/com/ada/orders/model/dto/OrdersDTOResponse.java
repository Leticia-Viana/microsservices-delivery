package br.com.ada.orders.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdersDTOResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long orderId;

    private String clientName;

    private String deliveryAddress;
}
