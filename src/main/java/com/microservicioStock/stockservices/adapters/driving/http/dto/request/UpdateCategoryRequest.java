package com.microservicioStock.stockservices.adapters.driving.http.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateCategoryRequest {
    private Long id;
    private String name;
    private String description;
}