package com.microservicioStock.stockService.adapters.driven.jpa.mysql.adapter;

import com.microservicioStock.stockService.adapters.driven.jpa.mysql.entity.CategoryEntity;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.mapper.ICategoryEntityMapper;
import com.microservicioStock.stockService.adapters.driven.jpa.mysql.repository.ICategoryRepository;
import com.microservicioStock.stockService.domain.model.Category;
import com.microservicioStock.stockService.domain.spi.ICategoryPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@RequiredArgsConstructor
public class CategoryAdapter implements ICategoryPersistencePort {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.findByName(category.getName()).isPresent()) {
            throw new CategoryAlreadyExistsException();
        }
        categoryRepository.save(categoryEntityMapper.toEntity(category));
    }

    @Override
    public Category getCategoryById(Long id) {
        CategoryEntity categoryEntity = categoryRepository.findById(id)
                .orElseThrow(ElementNotFoundException::new);
        return categoryEntityMapper.toModel(categoryEntity);
    }

    @Override
    public List<Category> getAllCategories(Integer page, Integer size) {
        Pageable pagination = PageRequest.of(page, size);
        List<CategoryEntity> categories = categoryRepository.findAll(pagination).getContent();
        if (categories.isEmpty()) {
            throw new NoDataFoundException();
        }
        return categoryEntityMapper.toModelList(categories);
    }

    @Override
    public Category updateCategory(Category category) {
        if (categoryRepository.findById(category.getId()).isEmpty()) {
            throw new ElementNotFoundException();
        }
        return categoryEntityMapper.toModel(categoryRepository.save(categoryEntityMapper.toEntity(category)));
    }

    @Override
    public void deleteCategory(Long id) {
        if (categoryRepository.findById(id).isEmpty()) {
            throw new ElementNotFoundException();
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return false;
    }
}
