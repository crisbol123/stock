package com.microservicio_stock.stock_service.domain.model;

import com.microservicio_stock.stock_service.domain.exception.DuplicateCategoryException;
import com.microservicio_stock.stock_service.domain.exception.EmptyFieldException;
import com.microservicio_stock.stock_service.domain.exception.InvalidCategoryCountException;
import com.microservicio_stock.stock_service.domain.util.DomainConstants;

import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Article {
    private Long id;
    private String name;
    private String description;
    private int quantity;
    private double price;
    private List<Category> categories;
    private Mark mark;

    public Article(Long id, String name, String description, int quantity, double price, List<Category> categories, Mark mark) {
        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description == null || description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if (categories == null || categories.size() < DomainConstants.MINIMUM_CATEGORIES || categories.size() > DomainConstants.MAXIMUM_CATEGORIES) {
            throw new InvalidCategoryCountException();
        }
        Set<Category> uniqueCategories = new HashSet<>(categories);
        if (uniqueCategories.size() != categories.size()) {
            throw new DuplicateCategoryException();
        }

        this.id = id;
        this.name = name;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.categories = categories;
        this.mark = mark;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Mark getMark() {
        return mark;
    }

    public void setMark(Mark mark) {
        this.mark = mark;
    }
}