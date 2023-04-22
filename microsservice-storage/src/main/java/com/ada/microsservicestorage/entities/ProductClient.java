package com.ada.microsservicestorage.entities;

import com.ada.microsservicestorage.dto.ProductResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "products", url = "localhost:8081")
public interface ProductClient {

    @GetMapping("/products/{id}")
    ProductResponseDTO getProduct (@PathVariable("id") Long id);
}
