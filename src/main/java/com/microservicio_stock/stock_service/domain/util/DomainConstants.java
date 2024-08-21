package com.microservicio_stock.stock_service.domain.util;

public final class DomainConstants {
    private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION
    }

    public static final int MAXIMUM_LENGTH_NAME = 50;
    public static final int MAXIMUM_LENGTH_DESCRIPTION = 50;
}
