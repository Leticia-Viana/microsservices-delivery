package br.com.ada.microservice.delivery.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDERS")
public class OrderEntity {

    @Id
    private Long id;
    private String clientName;
    private String deliveryAddress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="deliveriesEntity_id")
    private DeliveriesEntity deliveriesEntity;

}
