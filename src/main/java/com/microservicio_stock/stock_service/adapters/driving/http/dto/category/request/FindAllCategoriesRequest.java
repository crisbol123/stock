package com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request;

public class FindAllCategoriesRequest {
    private Integer page;
    private Integer size;
    private boolean ascOrderByName;

    public FindAllCategoriesRequest(Integer page, Integer size, boolean ascOrderByName) {
        this.page = page;
        this.size = size;
        this.ascOrderByName = ascOrderByName;
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
