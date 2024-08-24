

package com.microservicio_stock.stock_service.adapters.driving.http.dto.category.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCategoryRequest {

    private String name;
    private String description;
}