package br.com.ada.orders.model.mapper;

import br.com.ada.orders.model.dto.OrdersDTORequest;
import br.com.ada.orders.model.dto.OrdersDTOResponse;
import br.com.ada.orders.model.entity.OrdersEntity;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class OrdersMapper {

    public OrdersDTOResponse dtoResponseToEntity(OrdersEntity ordersEntity){
        if (Objects.isNull(ordersEntity)) return null;

        return  OrdersDTOResponse.builder()
                .clientName(ordersEntity.getClientName())
                .deliveryAddress(ordersEntity.getDeliveryAddress())
                .build();
    }

    public OrdersEntity dtoToEntity(OrdersDTORequest orderDTO){
        if (Objects.isNull(orderDTO)) return null;

        return  OrdersEntity.builder()
                .orderId(Long.MAX_VALUE)
                .requestDate(Date.from(Instant.now()))
                .deliveryAddress(orderDTO.getDeliveryAddress())
                .clientName(orderDTO.getClientName())
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

    public List<OrdersDTOResponse> listOrdersResponse(List<OrdersEntity> ordersEntityList){
        if (Objects.isNull(ordersEntityList)) return new ArrayList<>();

        return ordersEntityList.stream()
                .map(this::dtoResponseToEntity)
                .collect(Collectors.toList());
    }

}
