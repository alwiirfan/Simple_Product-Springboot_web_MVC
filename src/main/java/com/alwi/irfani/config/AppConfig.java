package com.alwi.irfani.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alwi.irfani.utils.RandomNumber;

@Configuration
public class AppConfig {
    
    @Bean
    public RandomNumber randomNumber() {
        return new RandomNumber();
    }
}
