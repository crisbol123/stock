package com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddMarkRequest {

    private String name;
    private String description;
}
