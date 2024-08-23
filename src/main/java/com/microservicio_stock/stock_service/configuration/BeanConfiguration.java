package com.microservicio_stock.stock_service.configuration;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter.CategoryAdapter;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter.MarkAdapter;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IMarkEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IMarkRepository;
import com.microservicio_stock.stock_service.domain.api.ICategoryServicePort;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.spi.IMarkPersistencePort;
import com.microservicio_stock.stock_service.domain.use_cases.CategoryUseCase;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;
import com.microservicio_stock.stock_service.domain.use_cases.MarkUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    private final IMarkEntityMapper markEntityMapper;
    private final IMarkRepository markRepository;




    @Bean
    public ICategoryPersistencePort categoryPersistencePort() {
        return new CategoryAdapter(categoryRepository, categoryEntityMapper);
    }

    @Bean
    public ICategoryServicePort categoryServicePort() {

        return new CategoryUseCase(categoryPersistencePort());
    }
       @Bean
    public IMarkPersistencePort markPersistencePort() {
    return new MarkAdapter(markRepository, markEntityMapper);
    }
    @Bean
   public IMarkServicePort markServicePort() {
        return new MarkUseCase(markPersistencePort());
    }


}

