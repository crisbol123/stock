package com.microservicioStock.stockService.configuration;

import com.microservicioStock.stockService.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicioStock.stockService.domain.api.ICategoryServicePort;
import com.microservicioStock.stockService.domain.useCases.CategoryUseCase;
import com.microservicioStock.stockService.domain.spi.ICategoryPersistencePort;
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
