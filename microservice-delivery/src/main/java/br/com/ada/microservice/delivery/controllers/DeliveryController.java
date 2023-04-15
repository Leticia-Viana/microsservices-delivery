package br.com.ada.microservice.delivery.controllers;

import javax.validation.Valid;

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

import br.com.ada.microservice.delivery.dto.DeliveryDTO;
import br.com.ada.microservice.delivery.services.DeliveryService;

@RestController
@RequestMapping("entregas")
public class DeliveryController {

	@Autowired
	private DeliveryService deliveryService;
	
	@PostMapping("")
	public ResponseEntity<DeliveryDTO> save(@Valid @RequestBody DeliveryDTO delivery){
		return ResponseEntity.status(HttpStatus.OK).body(deliveryService.save(delivery.toEntity()));
	}
	
	@PatchMapping("{id}")
	public ResponseEntity<DeliveryDTO> update(@PathVariable Long id, @RequestBody DeliveryDTO delivery) {
		return ResponseEntity.status(HttpStatus.OK).body(deliveryService.update(id, delivery.toEntity()));
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity <Void> delete(@PathVariable Long id) {
		deliveryService.delete(id);
		return  ResponseEntity.ok().build();
	}
}
