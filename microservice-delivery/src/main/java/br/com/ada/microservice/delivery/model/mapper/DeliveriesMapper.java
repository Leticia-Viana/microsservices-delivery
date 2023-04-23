package br.com.ada.microservice.delivery.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import br.com.ada.microservice.delivery.model.dto.DeliveriesRequestDTO;
import br.com.ada.microservice.delivery.model.dto.DeliveriesResponseDTO;
import br.com.ada.microservice.delivery.model.entity.DeliveriesEntity;

@Service
public class DeliveriesMapper {

//	public DeliveriesResponseDTO dtoResponseToEntity(DeliveriesEntity deliveriesEntity) {
//		if(Objects.isNull(deliveriesEntity)) return null;
//
//		return DeliveriesResponseDTO.builder()
//				.deliver(deliveriesEntity.getDeliveryAddress())
//				.list_orders(deliveriesEntity.getClientName())
//				.build();
//	}

	public DeliveriesResponseDTO entityToResponse(DeliveriesEntity deliveriesEntity){
		if(Objects.isNull(deliveriesEntity)) return null;

		return DeliveriesResponseDTO.builder()
				.id(deliveriesEntity.getId()).build();
	}
	
//	public DeliveriesEntity dtoToEntity(DeliveriesRequestDTO deliveriesDTO){
//        if (Objects.isNull(deliveriesDTO)) return null;
//
//        return  DeliveriesEntity.builder()
//                .deliveryAddress(deliveriesDTO.getDeliveryAddress())
//                .clientName(deliveriesDTO.getClientName())
//                .build();
//    }
//
//    public DeliveriesResponseDTO requestDtoToResponse(DeliveriesRequestDTO deliveriesDTO){
//        if (Objects.isNull(deliveriesDTO)) {
//            return null;
//        } else {
//            return DeliveriesResponseDTO.builder()
//                    .deliveryAddress(deliveriesDTO.getDeliveryAddress())
//                    .clientName(deliveriesDTO.getClientName())
//                    .build();
//        }
//    }

//    public List<DeliveriesResponseDTO> listDeliveriesResponse(List<DeliveriesEntity> deliveriesEntityList){
//        if (Objects.isNull(deliveriesEntityList)) return new ArrayList<>();
//
//        return deliveriesEntityList.stream()
//                .map(this::dtoResponseToEntity)
//                .collect(Collectors.toList());
//    }

}
