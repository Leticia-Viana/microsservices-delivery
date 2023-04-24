package br.com.ada.microservice.delivery.controllers;

import br.com.ada.microservice.delivery.model.dto.OrderRequestDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ada.microservice.delivery.model.dto.DeliveriesRequestDTO;
import br.com.ada.microservice.delivery.model.dto.DeliveriesResponseDTO;
import br.com.ada.microservice.delivery.services.interfaces.DeliveriesService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@RestController
@RequestMapping("/deliveries")
@Slf4j
@Api(value= "Deliveries API")
public class DeliveriesController {

	@Autowired
	private DeliveriesService deliveryService;
	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	
	@PostMapping()
	public ResponseEntity<DeliveriesResponseDTO> insertDeliveries(@RequestBody final OrderRequestDto orderRequestDto){
		log.info("- DeliveriesController --> Initialized insertDeliveries...");
		return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.saveDeliveries(orderRequestDto));
	}
	
//	@PatchMapping("{id}")
//	public ResponseEntity<DeliveriesResponseDTO> updateDeliveries(@PathVariable("id") Long id, @RequestBody DeliveriesRequestDTO deliveriesRequest) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(deliveryService.updateDeliveries(id, deliveriesRequest));
//	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<DeliveriesResponseDTO> deleteDeliveries(@PathVariable("id") Long id) {
		deliveryService.deleteDeliveriesById(id);
		return ResponseEntity.noContent().build();
	}
}
