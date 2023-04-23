package br.com.ada.microservice.delivery.services.impls;

import br.com.ada.microservice.delivery.model.dto.OrderRequestDto;
import br.com.ada.microservice.delivery.model.entity.DeliveriesEntity;
import br.com.ada.microservice.delivery.model.entity.OrderEntity;
import br.com.ada.microservice.delivery.model.mapper.OrderMapper;
import br.com.ada.microservice.delivery.repositories.OrderRepository;
import br.com.ada.microservice.delivery.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;


    @Override
    public OrderEntity saveOrder(final OrderRequestDto orderRequestDto, final DeliveriesEntity deliveriesEntity) {
        log.info("- OrderServiceImpl --> Entering order: {} in database", orderRequestDto);
        OrderEntity orderEntity = orderMapper.dtoToEntity(orderRequestDto);
        orderEntity.setDeliveriesEntity(deliveriesEntity);
        return orderRepository.save(orderEntity);
    }
}
