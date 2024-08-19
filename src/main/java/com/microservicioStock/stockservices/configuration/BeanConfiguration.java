package com.microservicioStock.stockservices.configuration;

import com.microservicioStock.stockservices.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.microservicioStock.stockservices.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicioStock.stockservices.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicioStock.stockservices.domain.api.ICategoryServicePort;
import com.microservicioStock.stockservices.domain.useCases.CategoryUseCase;
import com.microservicioStock.stockservices.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;

    private final ICategoryEntityMapper categoryEntityMapper;

    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {
        return new CategoryUseCase(categoryPersistencePort());
    }
}
