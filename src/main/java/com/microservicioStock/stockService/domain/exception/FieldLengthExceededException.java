package com.microservicioStock.stockService.domain.exception;

public class FieldLengthExceededException extends RuntimeException {
    private final String fieldName;
    private final int maxLength;

    public FieldLengthExceededException(String fieldName, int maxLength) {
        super(String.format("The field '%s' exceeds the maximum allowed length of %d characters.", fieldName, maxLength));
        this.fieldName = fieldName;
        this.maxLength = maxLength;
    }

    public String getFieldName() {
        return fieldName;
    }

    public int getMaxLength() {
        return maxLength;
    }
}