package br.com.ada.orders.model.mapper;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.model.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrdersMapper {

    public OrdersDTOResponse entityToResponseDTO(OrderEntity orderEntity){
        if (Objects.isNull(orderEntity)) return null;

        return  OrdersDTOResponse.builder()
                .orderId(orderEntity.getId())
                .clientName(orderEntity.getClientName())
                .deliveryAddress(orderEntity.getDeliveryAddress())
                .build();
    }

    public OrderEntity dtoToEntity(OrdersDTORequest orderDTO){
        if (Objects.isNull(orderDTO)) return null;

        return  OrderEntity.builder()
                .requestDate(Date.from(Instant.now()))
                .deliveryAddress(orderDTO.getDeliveryAddress())
                .clientName(orderDTO.getClientName())
//                .listProducts(orderDTO.getListProducts())
                .build();
    }

    public OrdersDTOResponse requestDtoToResponse(OrdersDTORequest ordersDTORequest){
        if (Objects.isNull(ordersDTORequest)) {
            return null;
        } else {
            return OrdersDTOResponse.builder()
                    .deliveryAddress(ordersDTORequest.getDeliveryAddress())
                    .clientName(ordersDTORequest.getClientName())
                    .build();
        }
    }

    public List<OrdersDTOResponse> listOrdersResponse(List<OrderEntity> orderEntityList){
        if (Objects.isNull(orderEntityList)) return new ArrayList<>();

        return orderEntityList.stream()
                .map(this::entityToResponseDTO)
                .collect(Collectors.toList());
    }

}
