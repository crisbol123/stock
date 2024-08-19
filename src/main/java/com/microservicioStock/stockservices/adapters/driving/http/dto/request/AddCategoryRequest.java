

package com.microservicioStock.stockservices.adapters.driving.http.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddCategoryRequest {
    private String name;
    private String description;
}