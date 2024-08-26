package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.response;

public class CategorySummaryResponse {
    private Long id;
    private String name;

    public CategorySummaryResponse(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CategorySummaryResponse() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
