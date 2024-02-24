package io.nozistance.dsme.configuration;

import io.nozistance.dsme.service.DataUpdatingService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class DataUpdatingConfig {

    @Bean
    @Order(Integer.MIN_VALUE)
    public ApplicationRunner dataUpdatingApplicationRunner(DataUpdatingService dataUpdatingService) {
        return args -> dataUpdatingService.update();
    }
}
