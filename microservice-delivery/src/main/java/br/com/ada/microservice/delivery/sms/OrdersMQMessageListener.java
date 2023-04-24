package br.com.ada.microservice.delivery.sms;

import br.com.ada.microservice.delivery.model.dto.OrderRequestDto;
import br.com.ada.microservice.delivery.services.interfaces.DeliveriesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrdersMQMessageListener {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    DeliveriesService deliveriesService;

    @RabbitListener(queues = "queueName")
    public void receiveOrders(String order) throws JsonProcessingException {
        OrderRequestDto orderRequest = objectMapper.readValue(order, OrderRequestDto.class);
        System.out.println("Received message: " + order);
        deliveriesService.saveDeliveries(orderRequest);
    }
}
