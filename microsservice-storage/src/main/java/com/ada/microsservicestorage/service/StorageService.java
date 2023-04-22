package com.ada.microsservicestorage.service;

import com.ada.microsservicestorage.dto.DecreseRequestDTO;
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
import org.springframework.data.util.Optionals;
import org.springframework.stereotype.Service;

import java.util.List;
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

        Optional<ProductEntity> productEntity = storageRepository.findById(productAddRequestDTO.getIdProduct());
        ProductEntity product = null;

        if(Optionals.isAnyPresent(productEntity)){
            product = productEntity.get();
            product.setQtdd(product.getQtdd() + productAddRequestDTO.getQtdd());
        } else {
            Long id = productAddRequestDTO.getIdProduct();
            ProductResponseDTO productResponseDTO = productClient.getProduct(id);
            product = productMapper.toEntity(productResponseDTO);
            product.setQtdd(productAddRequestDTO.getQtdd());
        }
        storageRepository.save(product);
        return productMapper.toDto(product);
    }

    public ProductStorageResponseDTO decreaseProduct(Long id, DecreseRequestDTO decreseRequestDTO) throws ProductStorageNotFoundError {
        log.info("- StorageService --> Initialized decreaseProduct...");

        Optional<ProductEntity> optional = storageRepository.findById(id);

        if(optional.isPresent()) {

            ProductEntity productBD = optional.get();
            Long result = productBD.getQtdd() - decreseRequestDTO.getQtdd();
            if(result < 0){
                //TODO: Validar retorno
            }
            productBD.setQtdd(result);
            return productMapper.toDto(storageRepository.save(productBD));
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
