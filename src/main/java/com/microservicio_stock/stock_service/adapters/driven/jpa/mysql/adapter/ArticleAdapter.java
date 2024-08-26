package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IArticleEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IArticleRepository;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.domain.api.IArticleServicePort;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.spi.IArticlePersistencePort;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@AllArgsConstructor
public class ArticleAdapter implements IArticlePersistencePort {

    private final IArticleRepository iArticleRepository;
    private final IArticleEntityMapper iArticleEntityMapper;


    @Override
    public void saveArticle(Article article) {
        iArticleRepository.save(iArticleEntityMapper.toEntity(article));
    }

    @Override
    public PagedResponse<Article> getPagedArticles(Integer page, Integer size, boolean ascOrderByName, String orderBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, orderBy));
        Page<ArticleEntity> articleEntities = iArticleRepository.findAll(pageable);
        if (articleEntities.isEmpty()) {
            throw new NoDataFoundException();
        }
        return iArticleEntityMapper.toPagedModel(articleEntities, articleEntities.isLast(), articleEntities.getNumber());
    }
}