package com.microservicio_stock.stock_service.domain.exception;

public class DuplicateCategoryException extends RuntimeException {
    public DuplicateCategoryException() {
        super("The article cannot have duplicate categories");
    }
}
