package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository;

import org.springframework.stereotype.Repository;


import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
@Repository

public interface ICategoryRepository extends JpaRepository<CategoryEntity, Long> {
    Optional<CategoryEntity> findByName(String name);
}
