package com.lirio.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {

    Flux<Product> getAll();
    Mono<Product> getById(String id);
    Mono<Product> save(Product product);
}

@Service
@RequiredArgsConstructor
class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Flux<Product> getAll() {
        throw new RuntimeException("Not Implemented");
    }

    @Override
    public Mono<Product> getById(String id) {
        return productRepository.getById(id);
    }

    @Override
    public Mono<Product> save(Product product) {
        return productRepository.save(product);
    }
}