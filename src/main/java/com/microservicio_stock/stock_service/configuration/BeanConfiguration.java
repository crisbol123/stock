package com.microservicio_stock.stock_service.configuration;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.use_cases.CategoryUseCase;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;
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
