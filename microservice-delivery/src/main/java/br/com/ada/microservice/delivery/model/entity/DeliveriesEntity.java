package br.com.ada.microservice.delivery.model.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DELIVERIES")
public class DeliveriesEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String status;
	@OneToMany(mappedBy = "deliveriesEntity", fetch = FetchType.EAGER)
	private List<OrderEntity> orders = new ArrayList<>();
	private Boolean isFull;
	
}
