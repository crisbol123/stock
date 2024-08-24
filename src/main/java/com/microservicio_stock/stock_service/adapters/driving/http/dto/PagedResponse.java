package com.microservicio_stock.stock_service.adapters.driving.http.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter

public class PagedResponse<T> {
    private List<T> content;
    private int currentPage;
    private int totalPages;
    private long totalElements;
    private boolean lastPage;
    private boolean ascOrderByName;

    public PagedResponse(List<T> content, int currentPage, int totalPages, long totalElements, boolean lastPage, boolean ascOrderByName) {
        this.content = content;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.lastPage = lastPage;
        this.ascOrderByName = ascOrderByName;
    }


}
