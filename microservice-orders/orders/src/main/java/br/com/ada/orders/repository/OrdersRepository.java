package br.com.ada.orders.repository;

import br.com.ada.orders.model.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Long> {
}
