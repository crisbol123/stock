package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response;


import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ArticleResponse {

    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<CategorySummaryResponse> categories;
    private MarkResponse mark;
}