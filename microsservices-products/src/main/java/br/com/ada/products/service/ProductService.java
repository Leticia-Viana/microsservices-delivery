package br.com.ada.products.service;

import br.com.ada.products.model.Product;
import br.com.ada.products.model.dto.ProductRequest;
import br.com.ada.products.model.dto.ProductResponse;
import br.com.ada.products.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse get(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        if(optional.isPresent()) {
            Product product = optional.get();
            return ProductResponse.builder()
                    .name(product.getName())
                    .category(product.getCategory())
                    .id(product.getId())
                    .build();
        }
        return null;
    }

    public Set<ProductResponse> getAll() {
        return productRepository.findAll().stream().map(product ->
            ProductResponse.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .category(product.getCategory())
                    .build()
        ).collect(Collectors.toSet());
    }

    public Long create(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .category(productRequest.getCategory())
                .build();
        Product saved = productRepository.save(product);
        return saved.getId();
    }

    public void delete(Long id) {
        Optional<Product> optional = productRepository.findById(id);
        productRepository.delete(optional.orElse(Product.builder().build()));
    }
}
