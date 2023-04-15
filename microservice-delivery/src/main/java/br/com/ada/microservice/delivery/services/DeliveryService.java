package br.com.ada.microservice.delivery.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.microservice.delivery.dto.DeliveryDTO;
import br.com.ada.microservice.delivery.entities.DeliveryEntity;
import br.com.ada.microservice.delivery.repositories.DeliveryRepository;

@Service
public class DeliveryService {

	@Autowired
	private DeliveryRepository deliveryRepository;
	
	public DeliveryDTO save(DeliveryEntity delivery) {
		return deliveryRepository.save(delivery).toDto();		
	}
	
	public DeliveryDTO update(Long id, DeliveryEntity delivery) {

		Optional<DeliveryEntity> optional = deliveryRepository.findById(id);

		if(optional.isPresent()) {

			DeliveryEntity deliveryBD = optional.get();
			deliveryBD.setDeliver(delivery.getDeliver());
			deliveryBD.setList_orders(delivery.getList_orders());
						
			return deliveryRepository.save(deliveryBD).toDto();
		}

		else {
			return new DeliveryEntity().toDto();
		}
	}

	public void delete(Long id) {
		deliveryRepository.deleteById(id);		
	}
}
