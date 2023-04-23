package br.com.ada.microservice.delivery.services.interfaces;

import br.com.ada.microservice.delivery.model.dto.OrderRequestDto;
import org.springframework.stereotype.Service;

import br.com.ada.microservice.delivery.model.dto.DeliveriesRequestDTO;
import br.com.ada.microservice.delivery.model.dto.DeliveriesResponseDTO;

@Service
public interface DeliveriesService {

	public DeliveriesResponseDTO saveDeliveries(OrderRequestDto orderRequestDto);
	
//	public DeliveriesResponseDTO updateDeliveries(Long id, DeliveriesRequestDTO deliveriesRequest);
	
	public void deleteDeliveriesById(Long id);

}
