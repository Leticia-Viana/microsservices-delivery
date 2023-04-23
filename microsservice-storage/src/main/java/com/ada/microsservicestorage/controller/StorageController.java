package com.ada.microsservicestorage.controller;


import com.ada.microsservicestorage.dto.DecreseRequestDTO;
import com.ada.microsservicestorage.dto.ProductAddRequestDTO;
import com.ada.microsservicestorage.dto.ProductDTO;
import com.ada.microsservicestorage.dto.ProductStorageResponseDTO;
import com.ada.microsservicestorage.erros.ProductStorageNotFoundError;
import com.ada.microsservicestorage.service.StorageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/storage")
@Api(value = "Storage API")
public class StorageController {

    private final StorageService storageService;

    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @ApiOperation(value = "Return all storage in database")
    @GetMapping("/listAllProductsStorage")
    public ResponseEntity<List<ProductStorageResponseDTO>> listAllProductsStorage(){
        log.info("- StorageController --> Initialized listAllProductsStorage...");
        return ResponseEntity.ok(storageService.listAllProductsStorage());
    }

    @PostMapping("")
    public ResponseEntity <ProductStorageResponseDTO> addProduct(@Valid @RequestBody ProductAddRequestDTO productAddRequestDTO){
        log.info("- StorageController --> Initialized addProduct...");
        return ResponseEntity.status(HttpStatus.OK).body(storageService.addProduct(productAddRequestDTO));
    }

    @PatchMapping("{id}")
    public ResponseEntity<ProductStorageResponseDTO> decreaseProduct(@PathVariable Long id, @RequestBody DecreseRequestDTO decreseRequestDTO) {
        log.info("- StorageController --> Initialized decreaseProduct...");
        try {
            return ResponseEntity.status(HttpStatus.OK).body(storageService.decreaseProduct(id, decreseRequestDTO));
        } catch (ProductStorageNotFoundError productStorageNotFoundError){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
