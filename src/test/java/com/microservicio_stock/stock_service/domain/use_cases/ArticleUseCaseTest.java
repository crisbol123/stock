package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.model.Article;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IArticlePersistencePort;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

public class ArticleUseCaseTest {

    @Mock
    private IArticlePersistencePort iArticlePersistencePort;

    @InjectMocks
    private ArticleUseCase articleUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveArticle() {
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        Mark mark = new Mark(1L, "Mark 1", "Mark Description");

        Article article = new Article(1L, "Article 1", "Description", 10, 100.0, categories, mark);

        articleUseCase.saveArticle(article);


        Mockito.verify(iArticlePersistencePort, Mockito.times(1)).saveArticle(article);
    }

    @Test
    void testGetPagedArticles() {
        // Arrange
        int page = 0;
        int size = 10;
        boolean ascOrderByName = true;
        String orderBy = "name";
        List<Category> categories = Arrays.asList(
                new Category(1L, "Category 1", "Description 1"),
                new Category(2L, "Category 2", "Description 2")
        );

        Mark mark = new Mark(1L, "Mark 1", "Mark Description");
        Article article1 = new Article(1L, "Article 1", "Description", 10, 100.0,  categories, mark);
        List<Category> categories2 = Arrays.asList(
                new Category(1L, "Category 3", "Description 3"),
                new Category(2L, "Category 4", "Description 4")
        );

        Mark mark2 = new Mark(1L, "Mark 2", "Mark Description");


        Article article2 = new Article(2L, "Article 2", "Description", 15, 150.0, categories2, mark2);

        List<Article> articles = Arrays.asList(article1, article2);
        PagedResponse<Article> pagedResponse = new PagedResponse<>(articles, page, 1, 2L, true);

        Mockito.when(iArticlePersistencePort.getPagedArticles(page, size, ascOrderByName, orderBy)).thenReturn(pagedResponse);

        PagedResponse<Article> result = articleUseCase.getPagedArticles(page, size, ascOrderByName, orderBy);

        assertEquals(2, result.getTotalElements());
        assertEquals(articles, result.getContent());
        Mockito.verify(iArticlePersistencePort, Mockito.times(1)).getPagedArticles(page, size, ascOrderByName, orderBy);
    }
}
