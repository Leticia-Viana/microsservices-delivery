package br.com.ada.microservice.delivery.dto;

import org.modelmapper.ModelMapper;

import br.com.ada.microservice.delivery.entities.DeliveryEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeliveryDTO {

	private Long id;
	
	@NotEmpty(message = "Preenchimento Obrigatório")
	@NotNull(message = "Preenchimento Obrigatório")
	@Length(min = 3, max= 80, message= "O número de caracteres deve ser entre 3 e 80")
	@Pattern( regexp = "^[A-Za-záàâãéèêíïóôõöúçñÁÀÂÃÉÈÍÏÓÔÕÖÚÇÑ ]*$", message = "É valido apenas caracteres")
	private String deliver;
	
	private String list_orders;
	
	public DeliveryEntity toEntity() {
		ModelMapper mapper = new ModelMapper();
		DeliveryEntity entity = mapper.map(this, DeliveryEntity.class);
		return entity;
	}
}
