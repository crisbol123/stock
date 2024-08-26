package com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request;

public class FindAllRequest {
    private Integer page;
    private Integer size;
    private boolean ascOrderByName;

    public FindAllRequest(Integer page, Integer size, boolean ascOrderByName) {
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
