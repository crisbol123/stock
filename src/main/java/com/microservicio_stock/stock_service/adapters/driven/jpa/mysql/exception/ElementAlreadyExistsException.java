package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception;

public class ElementAlreadyExistsException extends RuntimeException {
    public ElementAlreadyExistsException( String message) {
        super( message);
    }
}
