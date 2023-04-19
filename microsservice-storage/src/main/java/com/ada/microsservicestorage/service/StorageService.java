package com.ada.microsservicestorage.service;

import com.ada.microsservicestorage.dto.ProductAddRequestDTO;
import com.ada.microsservicestorage.dto.ProductResponseDTO;
import com.ada.microsservicestorage.dto.ProductStorageResponseDTO;
import com.ada.microsservicestorage.entities.ProductClient;
import com.ada.microsservicestorage.entities.ProductEntity;
import com.ada.microsservicestorage.erros.ProductStorageNotFoundError;
import com.ada.microsservicestorage.mappers.ProductMapper;
import com.ada.microsservicestorage.repository.StorageRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class StorageService {


    private final StorageRepository storageRepository;
    private final ProductMapper productMapper;
    private final ProductClient productClient;


    public ProductStorageResponseDTO addProduct(ProductAddRequestDTO productAddRequestDTO) {
        log.info("- StorageService --> Initialized addProduct...");

        Optional<ProductEntity> productEntity = Optional.of(storageRepository.getReferenceById(productAddRequestDTO.getIdProduct()));
        ProductEntity product = null;

        if(Optionals.isAnyPresent(productEntity)){
            product = productEntity.get();
            product.setQtdd(product.getQtdd() + productAddRequestDTO.getQtdd());
        } else {
            ProductResponseDTO productResponseDTO = productClient.getProduct(productAddRequestDTO.getIdProduct());
            product = productMapper.toEntity(productResponseDTO);
            product.setQtdd(productAddRequestDTO.getQtdd());
        }
        storageRepository.save(product);
        return productMapper.toDto(product);
    }

    public ProductStorageResponseDTO decreaseProduct(Long id, ProductEntity product) throws ProductStorageNotFoundError {
        log.info("- StorageService --> Initialized decreaseProduct...");

        Optional<ProductEntity> optional = storageRepository.findById(id);

        if(optional.isPresent()) {

            ProductEntity productBD = optional.get();
            productBD.setName(product.getName());
            productBD.setCategory(product.getCategory());
            storageRepository.save(productBD);
            return productMapper.toDto(product);
        }
        else {
            throw new ProductStorageNotFoundError();
        }
    }

    public List<ProductStorageResponseDTO> listAllProductsStorage() {
        log.info("- StorageService --> Initialized listAllProductsStorage...");
       return storageRepository.findAll().stream().map(productMapper::toDto).collect(Collectors.toList());
    }


}
