package br.com.ada.microservice.delivery.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import br.com.ada.microservice.delivery.dto.DeliveryDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ENTREGAS")
public class DeliveryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String deliver;
	private String list_orders;

	public DeliveryDTO toDto() {
		ModelMapper mapper = new ModelMapper();
		DeliveryDTO dto = mapper.map(this, DeliveryDTO.class);
		return dto;
	}
}
