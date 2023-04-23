package br.com.ada.orders.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Product_Order")
public class ProductOrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "orderEntity_id", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne
    @JoinColumn(name = "productEntity_id", nullable = false)
    private ProductEntity productEntity;

    private Long quantity;

}
