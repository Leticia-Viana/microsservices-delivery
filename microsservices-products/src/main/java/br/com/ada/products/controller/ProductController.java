package br.com.ada.products.controller;

import br.com.ada.products.model.Product;
import br.com.ada.products.model.dto.CreatedStatusResponse;
import br.com.ada.products.model.dto.ProductRequest;
import br.com.ada.products.model.dto.ProductResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getById(@PathParam("id") Long id) {
        return ResponseEntity.ok().body(
                ProductResponse.builder()
                        .id(id)
                        .name("Product Mocked")
                        .category("Category Mocked")
                        .build()
        );
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<ProductResponse>> listAll() {
        Set<ProductResponse> productsResponse = new HashSet<>();
        productsResponse.add(
                ProductResponse.builder()
                        .id(1L)
                        .name("Product Mocked")
                        .category("Category Mocked")
                        .build()
        );
        productsResponse.add(
                ProductResponse.builder()
                        .id(2L)
                        .name("Product Mocked Two")
                        .category("Category Mocked Two")
                        .build()
        );
        return ResponseEntity.ok().body(productsResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity createProduct(@RequestBody Product product) {

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(1L)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> updateProduct(@RequestBody ProductRequest productRequest) {

        return ResponseEntity.ok().body(
                ProductResponse.builder()
                        .name(productRequest.getName())
                        .category(productRequest.getCategory())
                        .build()
        );
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity delete(@PathParam("id") Long id) {
        return ResponseEntity.noContent().build();
    }
}
