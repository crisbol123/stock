package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Pattern;

public class FindAllArticlesRequest {

    @NotNull(message = "Page number must not be null")
    @Min(value = 0, message = "Page number must be 0 or greater")
    private Integer page;

    @NotNull(message = "Page size must not be null")
    @Positive(message = "Page size must be greater than 0")
    private Integer size;

    private boolean ascOrderByName;

    @NotBlank(message = "Order by field must not be blank")
    @Pattern(regexp = "article|category|mark",
            message = "Order by field must be one of the allowed values:article,category,mark")
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