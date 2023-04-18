package br.com.ada.microservice.delivery.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.ada.microservice.delivery.model.entity.DeliveriesEntity;

@Repository
public interface DeliveriesRepository extends JpaRepository<DeliveriesEntity, Long>{

}
