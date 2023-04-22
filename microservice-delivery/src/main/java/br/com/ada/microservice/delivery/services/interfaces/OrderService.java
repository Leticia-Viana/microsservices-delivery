package br.com.ada.microservice.delivery.services.interfaces;

import br.com.ada.microservice.delivery.model.dto.OrderRequestDto;
import br.com.ada.microservice.delivery.model.entity.DeliveriesEntity;
import br.com.ada.microservice.delivery.model.entity.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public OrderEntity saveOrder(final OrderRequestDto orderRequestDto, final DeliveriesEntity deliveriesEntity);

}
