package com.microservicio_stock.stock_service.adapters.driving.http.dto.category.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryResponse {
    private Long id;
    private String name;
    private String description;
}
