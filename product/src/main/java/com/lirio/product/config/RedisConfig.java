package com.lirio.product.config;

import com.lirio.product.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    //@Bean
    public ReactiveRedisConnectionFactory reactiveRedisConnectionFactory(@Value("${spring.redis.host}") String host,
                                                                         @Value("${spring.redis.port}") final int port) {
        return new LettuceConnectionFactory(host, port);
    }

//    @Bean
//    public ReactiveRedisTemplate<String, String> reactiveRedisTemplateString
//            (ReactiveRedisConnectionFactory connectionFactory) {
//        return new ReactiveRedisTemplate<>(connectionFactory, RedisSerializationContext.string());
//    }

    @Bean
    public ReactiveRedisTemplate<String, Product> reactiveRedisTemplate(
            @Value("${spring.redis.host}") String host,
            @Value("${spring.redis.port}") final int port) {

        StringRedisSerializer keySerializer = new StringRedisSerializer();
        Jackson2JsonRedisSerializer<Product> valueSerializer =
                new Jackson2JsonRedisSerializer<>(Product.class);
        RedisSerializationContext.RedisSerializationContextBuilder<String, Product> builder =
                RedisSerializationContext.newSerializationContext(keySerializer);
        RedisSerializationContext<String, Product> context =
                builder.value(valueSerializer).build();

        return new ReactiveRedisTemplate<>(reactiveRedisConnectionFactory(host, port), context);
    }
}
