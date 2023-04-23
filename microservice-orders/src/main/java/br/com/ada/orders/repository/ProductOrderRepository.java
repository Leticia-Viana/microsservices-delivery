package br.com.ada.orders.repository;

import br.com.ada.orders.model.entity.ProductOrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductOrderRepository  extends JpaRepository<ProductOrderEntity, Long> {
}
