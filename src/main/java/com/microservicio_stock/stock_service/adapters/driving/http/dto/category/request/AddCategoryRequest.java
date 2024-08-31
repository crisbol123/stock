package com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCategoryRequest {

    @NotBlank(message = "The name must not be empty")
    @Size(max = 50, message = "The name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "The description must not be empty")
    @Size(max = 90, message = "The description must not exceed 90 characters")
    private String description;
}
