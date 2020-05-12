package com.lirio.product;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.BodyInserters.fromPublisher;

@Configuration
public class ProductRoute {

    @Bean
    public RouterFunction<ServerResponse> route(CustomerHandler handler) {
        return RouterFunctions
                .route(GET("/products").and(accept(APPLICATION_JSON)), handler::getAll)
                .andRoute(GET("/products/{id}").and(accept(APPLICATION_JSON)), handler::getById)
                .andRoute(POST("/products").and(accept(APPLICATION_JSON)), handler::save);
    }

}

@Component
@RequiredArgsConstructor
class CustomerHandler {

    private final ProductService service;

    public Mono<ServerResponse> getAll(ServerRequest serverRequest) {
        Flux<Product> customers = service.getAll();
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(customers, Product.class);
    }

    public Mono<ServerResponse> getById(ServerRequest serverRequest) {
        Mono<Product> customer = service.getById(serverRequest.pathVariable("id"));
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(customer, Product.class);
    }

    public Mono<ServerResponse> save(ServerRequest serverRequest) {
        Mono<Product> _mono = serverRequest.bodyToMono(Product.class);
        return ServerResponse.ok().contentType(APPLICATION_JSON)
                .body(fromPublisher(_mono.flatMap(service::save), Product.class));
    }
}