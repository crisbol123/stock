package com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMarkRequest {

    @NotBlank(message = "The name must not be empty")
    @Size(max = 50, message = "The name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "The description must not be empty")
    @Size(max = 120, message = "The description must not exceed 120 characters")
    private String description;
}
