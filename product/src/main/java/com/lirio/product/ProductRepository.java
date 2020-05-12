package com.lirio.product;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;

public interface ProductRepository {
    Mono<Product> save(Product product);
    Mono<Product> getById(String id);
}

@Repository
class ProductRepositoryImpl implements ProductRepository {

    private final ReactiveRedisTemplate<String, Product> redisTemplate;
    private ReactiveValueOperations<String, Product> reactiveValueOps;

    ProductRepositoryImpl(ReactiveRedisTemplate<String, Product> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.reactiveValueOps = redisTemplate.opsForValue();
    }

    @Override
    public Mono<Product> save(Product product) {
        product.setId(UUID.randomUUID().toString());
        reactiveValueOps.set(product.getId(), product);
        return reactiveValueOps.get(product.getId());
    }

    @Override
    public Mono<Product> getById(String id) {
        return reactiveValueOps.get(id);
    }

}