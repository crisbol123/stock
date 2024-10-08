package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;


@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findIdByName(category.getName()).isPresent()) {
            throw new ElementAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }


    @Override
    public List<Category> getAllCategories(Integer page, Integer size, boolean ascOrderByName) {
        Sort sort = ascOrderByName ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pagination = PageRequest.of(page, size, sort);

        List<CategoryEntity> categories = categoryRepository.findAll(pagination).getContent();
        if (categories.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toModelList(categories);
    }

    @Override
    public long getTotalCategories() {
        return categoryRepository.count();
    }
}


