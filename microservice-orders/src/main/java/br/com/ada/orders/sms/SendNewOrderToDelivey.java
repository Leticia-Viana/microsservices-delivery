package br.com.ada.orders.sms;

import br.com.ada.orders.model.dto.OrdersDTOResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class SendNewOrderToDelivey {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Queue queue;

    public void sendOrder(OrdersDTOResponse orderResponse) {
        log.info("Sending new order to delivery microservice: {}", orderResponse);
        try {
            String message = objectMapper.writeValueAsString(orderResponse);
            rabbitTemplate.convertAndSend(queue.getName(), message);
        } catch (JsonProcessingException e) {
            log.error("Object conversion error");
            throw new RuntimeException(e);
        }

    }

}
