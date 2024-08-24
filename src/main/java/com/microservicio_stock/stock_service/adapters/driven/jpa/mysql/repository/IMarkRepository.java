package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository;

import org.springframework.stereotype.Repository;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface IMarkRepository extends JpaRepository<MarkEntity, Long> {
    Optional<MarkEntity> findByName(String name);
}