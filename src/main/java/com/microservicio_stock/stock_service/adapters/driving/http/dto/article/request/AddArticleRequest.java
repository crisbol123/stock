package com.microservicio_stock.stock_service.adapters.driving.http.dto.article.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class AddArticleRequest {

    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Long> categoryIds; // IDs of categories
    private Long markId; // ID of the mark
}