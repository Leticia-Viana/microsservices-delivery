package br.com.ada.microservice.delivery.services.interfaces;

import org.springframework.stereotype.Service;

import br.com.ada.microservice.delivery.model.dto.DeliveriesRequestDTO;
import br.com.ada.microservice.delivery.model.dto.DeliveriesResponseDTO;

@Service
public interface DeliveriesService {

	public DeliveriesResponseDTO saveDeliveries(DeliveriesRequestDTO deliveries);
	
	public DeliveriesResponseDTO updateDeliveries(Long id, DeliveriesRequestDTO deliveriesRequest);
	
	public void deleteDeliveriesById(Long id);

}
