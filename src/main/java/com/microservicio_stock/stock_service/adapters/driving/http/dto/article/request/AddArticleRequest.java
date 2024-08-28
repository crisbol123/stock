package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddArticleRequest {
@Size
    private String name;
    private String description;
    private int quantity;
    private double price;

    private List<Long> categoryIds;
    private Long markId;
}