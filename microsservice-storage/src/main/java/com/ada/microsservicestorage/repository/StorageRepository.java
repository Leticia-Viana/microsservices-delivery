package com.ada.microsservicestorage.repository;

import com.ada.microsservicestorage.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository  extends JpaRepository<ProductEntity, Long>{

}
