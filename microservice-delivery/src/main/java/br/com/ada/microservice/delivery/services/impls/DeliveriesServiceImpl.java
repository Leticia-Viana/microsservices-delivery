package br.com.ada.microservice.delivery.services.impls;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ada.microservice.delivery.model.dto.DeliveriesRequestDTO;
import br.com.ada.microservice.delivery.model.dto.DeliveriesResponseDTO;
import br.com.ada.microservice.delivery.model.entity.DeliveriesEntity;
import br.com.ada.microservice.delivery.model.mapper.DeliveriesMapper;
import br.com.ada.microservice.delivery.repositories.DeliveriesRepository;
import br.com.ada.microservice.delivery.services.interfaces.DeliveriesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class DeliveriesServiceImpl implements DeliveriesService {

	private final DeliveriesMapper deliveriesMapper = new DeliveriesMapper();
	
	@Autowired
	private DeliveriesRepository deliveriesRepository;
	
	@Override
	public DeliveriesResponseDTO saveDeliveries(DeliveriesRequestDTO deliveriesRequest) {
		log.info("- DeliveriesServiceImpl --> Entering deliver: {} in database", deliveriesRequest);
		deliveriesRepository.save(deliveriesMapper.dtoToEntity(deliveriesRequest));
		return deliveriesMapper.requestDtoToResponse(deliveriesRequest);
	}
	
	@Override
    public DeliveriesResponseDTO updateDeliveries(Long id, DeliveriesRequestDTO deliveriesRequest) {
        log.info("- DeliveriesServiceImpl --> Updating order: {} in database", deliveriesRequest);
        log.info("- DeliveriesServiceImpl --> Updating order by id: {} in database", id);

        Optional<DeliveriesEntity> optionalDeliver = deliveriesRepository.findById(id);
        optionalDeliver.orElseThrow(() -> new EntityNotFoundException("Deliver not found."));

        DeliveriesEntity entity = deliveriesMapper.dtoToEntity(deliveriesRequest);
//        entity.setDeliverId(id);

        deliveriesRepository.save(entity);
        return deliveriesMapper.dtoResponseToEntity(entity);
    }

    @Override
    public void deleteDeliveriesById(Long id) {
        Optional<DeliveriesEntity> entityById = deliveriesRepository.findById(id);
        entityById.orElseThrow(() -> new EntityNotFoundException("Deliver not found."));
        log.info("Deleting deliver by id: {}", id);
        deliveriesRepository.deleteById(id);
    }
}
