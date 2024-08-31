package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.ArticleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import java.util.Optional;

public interface IArticleRepository extends JpaRepository<ArticleEntity, Long> {
    @Query("SELECT c.id FROM ArticleEntity c WHERE c.name = :name")
    Optional<Long> findIdByName(@Param("name") String name);



}