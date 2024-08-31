package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.ArticleEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IArticleEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IArticleRepository;
import com.microservicio_stock.stock_service.configuration.Constants;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.spi.IArticlePersistencePort;
import lombok.AllArgsConstructor;


import java.util.Comparator;
import java.util.List;

@AllArgsConstructor
public class ArticleAdapter implements IArticlePersistencePort {

    private final IArticleRepository iArticleRepository;
    private final IArticleEntityMapper iArticleEntityMapper;

    @Override
    public void saveArticle(Article article) {
        if (iArticleRepository.findIdByName(article.getName()).isPresent()) {
            throw new ElementAlreadyExistsException(Constants.entities.ARTICLE.toString());
        }
        iArticleRepository.save(iArticleEntityMapper.toEntity(article));
    }

    @Override
    public PagedResponse<Article> getPagedArticles(Integer page, Integer size, boolean ascOrderByName, String orderBy) {

        List<ArticleEntity> articleEntities = iArticleRepository.findAll();

        if (articleEntities.isEmpty()) {
            throw new NoDataFoundException();
        }

        List<Article> articles = iArticleEntityMapper.toModelList(articleEntities);


        articles.forEach(article ->
                article.getCategories().sort(Comparator.comparing(category -> category.getName().toLowerCase()))
        );

        Comparator<Article> articleComparator;

        if ("categories.name".equals(orderBy)) {

            articleComparator = Comparator
                    .comparing((Article a) -> a.getCategories().isEmpty() ? "" : a.getCategories().get(0).getName().toLowerCase());

        } else if ("name".equals(orderBy)) {

            articleComparator = Comparator.comparing(article -> article.getName().toLowerCase());

        } else if ("mark.name".equals(orderBy)) {

            articleComparator = Comparator
                    .comparing((Article a) -> a.getMark() != null ? a.getMark().getName().toLowerCase() : "")
                    .thenComparing(article -> article.getName().toLowerCase());

        } else {

            articleComparator = Comparator.comparing(article -> article.getName().toLowerCase());
        }


        if (!ascOrderByName) {
            articleComparator = articleComparator.reversed();
        }

        List<Article> sortedArticles = articles.stream()
                .sorted(articleComparator)
                .toList();

        int start = Math.min(page * size, sortedArticles.size());
        int end = Math.min((page + 1) * size, sortedArticles.size());
        List<Article> pagedArticles = sortedArticles.subList(start, end);

        int totalElements = sortedArticles.size();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean lastPage = (page + 1) >= totalPages;

        return new PagedResponse<>(pagedArticles, page, totalPages, totalElements, lastPage);
    }}