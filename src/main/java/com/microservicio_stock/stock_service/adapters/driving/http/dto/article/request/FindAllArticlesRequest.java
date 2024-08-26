package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request;

public class FindAllArticlesRequest {
    private Integer page;
    private Integer size;
    private boolean ascOrderByName;
    private String orderBy;

    public FindAllArticlesRequest(Integer page, Integer size, boolean ascOrderByName, String orderBy) {
        this.page = page;
        this.size = size;
        this.ascOrderByName = ascOrderByName;
        this.orderBy = orderBy;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getSize() {
        return size;
    }

    public boolean isAscOrderByName() {
        return ascOrderByName;
    }
}
