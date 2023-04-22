package br.com.ada.microservice.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ada.microservice.delivery.model.entity.DeliveriesEntity;

import java.util.Optional;

@Repository
public interface DeliveriesRepository extends JpaRepository<DeliveriesEntity, Long>{

    public Optional<DeliveriesEntity> findByIsFull(Boolean isFull);

}
