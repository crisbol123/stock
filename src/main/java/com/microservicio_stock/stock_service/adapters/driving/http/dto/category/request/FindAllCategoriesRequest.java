package com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FindAllCategoriesRequest {

    @NotNull(message = "Page number must not be null")
    @Min(value = 0, message = "Page number must be 0 or greater")
    private Integer page;

    @NotNull(message = "Page size must not be null")
    @Min(value = 1, message = "Page size must be at least 1")
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