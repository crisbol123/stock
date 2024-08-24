package com.microservicio_stock.stock_service.domain.exception;

public class InvalidCategoryCountException extends RuntimeException {
    public InvalidCategoryCountException() {
        super("The article must have between 1 and 3 categories");
    }
}
