package br.com.ada.microservice.delivery.model.mapper;

import br.com.ada.microservice.delivery.model.dto.OrderRequestDto;
import br.com.ada.microservice.delivery.model.entity.OrderEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class OrderMapper {

    public OrderEntity dtoToEntity(OrderRequestDto orderRequestDto) {
        if (Objects.isNull(orderRequestDto)) return null;

        return OrderEntity.builder()
                .id(orderRequestDto.getOrderId())
                .deliveryAddress(orderRequestDto.getDeliveryAddress())
                .clientName(orderRequestDto.getClientName())
                .build();
    }
}
