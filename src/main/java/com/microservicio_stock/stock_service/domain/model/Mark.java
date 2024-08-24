package com.microservicio_stock.stock_service.domain.model;

import com.microservicio_stock.stock_service.domain.exception.EmptyFieldException;
import com.microservicio_stock.stock_service.domain.exception.FieldLengthExceededException;
import com.microservicio_stock.stock_service.domain.util.DomainConstants;

public class Mark {
    private Long id;
    private String name;
    private String description;
    
    public Mark(Long id, String name, String description) {
        this.id = id;
        if(name == null || name.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.NAME.toString());
        }
        if(description == null || description.isEmpty()) {
            throw new EmptyFieldException(DomainConstants.Field.DESCRIPTION.toString());
        }

       if (name.length() > DomainConstants.MAXIMUM_LENGTH_NAME_MARK) {
            throw new FieldLengthExceededException(DomainConstants.Field.NAME.toString(), DomainConstants.MAXIMUM_LENGTH_NAME_MARK);
        }
        this.name = name;
        if (description.length() > DomainConstants.MAXIMUM_LENGTH_DESCRIPTION_MARK) {
            throw new FieldLengthExceededException( DomainConstants.Field.DESCRIPTION.toString(), DomainConstants.MAXIMUM_LENGTH_DESCRIPTION_MARK);
        }
        this.description = description;
    }
    
    // Getters
    
    public Long getId() {
        return id;
    }
    

    
    public String getName() {
        return name;
    }
    

    
    public String getDescription() {
        return description;
    }
    

}
