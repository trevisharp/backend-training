package com.trevis.backend.challenge;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.Configuration;

import com.trevis.backend.challenge.services.CollatzFunction;
import com.trevis.backend.challenge.services.DefaultCollatzFunction;

@Configuration
public class DependenciesConfiguration {
    
    @Bean
    @Scope("singleton")
    public CollatzFunction collatzFunction() {
        return new DefaultCollatzFunction();
    }
}
