package br.com.ada.products.controller;

import br.com.ada.products.model.Product;
import br.com.ada.products.model.dto.CreatedStatusResponse;
import br.com.ada.products.model.dto.ProductRequest;
import br.com.ada.products.model.dto.ProductResponse;
import br.com.ada.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductResponse> getById(@PathVariable("id") Long id) {

        ProductResponse response = productService.get(id);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<ProductResponse>> listAll() {

        Set<ProductResponse> listResponse = productService.getAll();
        return ResponseEntity.ok().body(listResponse);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<CreatedStatusResponse> createProduct(@RequestBody ProductRequest productRequest) {

        Long id = productService.create(productRequest);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(id)
                .toUri();

        return ResponseEntity.created(location).body(CreatedStatusResponse.builder().status("success").build());
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
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
