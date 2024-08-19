package com.microservicioStock.stockService.domain.model;

import com.microservicioStock.stockService.domain.exception.EmptyFieldException;
import com.microservicioStock.stockService.domain.exception.FieldLengthExceededException;
import com.microservicioStock.stockService.domain.util.DomainConstants;

public class Category {
    private Long id;
    private String name;
    private String description;

    public Category(Long id, String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if (description == null || description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }
        if (name.length() > 50) {
            throw new FieldLengthExceededException(DomainConstants.Field.NAME.toString(), 50);
        }
        if (description.length() > 90) {
            throw new FieldLengthExceededException(DomainConstants.Field.DESCRIPTION.toString(), 90);
        }


        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return description;
    }
}

