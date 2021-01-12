package com.spring.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import reactor.core.publisher.Mono;

/*
 *Created by olga on 12.01.2021
 */
@Configuration
public class GlobalFilterConfiguration {

    Logger logger = LoggerFactory.getLogger(GlobalFilterConfiguration.class);

    @Bean
    @Order(1)
    public GlobalFilter secondPreFilter(){
        logger.info("My Second global PreFilter is executed.......... ");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("My third global PostFilter is executed.....");
            }));
        };
    }

    @Bean
    @Order(2)
    public GlobalFilter thirdPreFilter(){
        logger.info("My third global PreFilter is executed.......... ");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("My second global PostFilter is executed.....");
            }));
        };
    }

    @Bean
    @Order(3)
    public GlobalFilter fourthPreFilter(){
        logger.info("My fourth global PreFilter is executed.......... ");
        return (exchange, chain) -> {
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                logger.info("My first global PostFilter is executed.....");
            }));
        };
    }
}
