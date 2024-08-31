package com.microservicio_stock.stock_service.configuration;

public class Constants {
    private Constants() {
        throw new IllegalStateException("Utility class");
    }
 public enum entities {
       ARTICLE, CATEGORY, MARK
        }

    // General Exception Messages
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
    public static final String ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE = "The specified element does not exist";
    public static final String ELEMENT_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The %s already exists";

    // Field Validation Messages
    public static final String EMPTY_FIELD_EXCEPTION_MESSAGE = "Field '%s' cannot be empty";
    public static final String FIELD_LENGTH_EXCEEDED_EXCEPTION_MESSAGE = "Field '%s' length exceeded the maximum allowed length of %d characters";

    // Article Specific Validation Messages
    public static final String NAME_NOT_BLANK = "Field 'name' cannot be emptyy";
    public static final String NAME_SIZE = "Field 'name' length exceeded the maximum allowed length of 50 characters";
    public static final String DESCRIPTION_NOT_BLANK = "Field 'description' cannot be empty";
    public static final String DESCRIPTION_SIZE = "Field 'description' length exceeded the maximum allowed length of 90 characters";
    public static final String QUANTITY_MIN = "Quantity must be at least 1";
    public static final String PRICE_POSITIVE = "Price must be greater than 0";
    public static final String CATEGORIES_NOT_EMPTY = "There must be at least one category";
    public static final String CATEGORIES_SIZE = "The number of categories must be between 1 and 3";
    public static final String CATEGORIES_UNIQUE = "Categories must be unique";
    public static final String MARK_ID_NOT_NULL = "Mark ID must not be null";
}
