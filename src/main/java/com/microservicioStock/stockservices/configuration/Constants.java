package com.microservicioStock.stockservices.configuration;

public class Constants {
    private Constants(){
        throw new IllegalStateException("Utility class");
    }

    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The element indicated does not exist";
    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category you want to create already exists";
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field '%s' cannot be empty";
    public static final String FIELD_LENGTH_EXCEEDED_EXCEPTION_MESSAGE = "Field '%s' length exceeded the maximum allowed length of %d characters";
    public static final String NEGATIVE_NOT_ALLOWED_EXCEPTION_MESSAGE = "Field '%s' cannot receive negative values";
}
