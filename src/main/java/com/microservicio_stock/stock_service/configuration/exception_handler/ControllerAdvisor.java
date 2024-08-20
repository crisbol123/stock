package com.microservicio_stock.stock_service.configuration.exception_handler;

import com.microservicio_stock.stock_service.domain.exception.EmptyFieldException;
import com.microservicio_stock.stock_service.domain.exception.FieldLengthExceededException;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementNotFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.CategoryAlreadyExistsException;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.configuration.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleEmptyFieldException(EmptyFieldException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.EMPTY_FIELD_EXCEPTION_MESSAGE, exception.getMessage()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(FieldLengthExceededException.class)
    public ResponseEntity<ExceptionResponse> handleFieldLengthExceededException(FieldLengthExceededException exception) {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                String.format(Constants.FIELD_LENGTH_EXCEEDED_EXCEPTION_MESSAGE,
                        exception.getFieldName(), exception.getMaxLength()),
                HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(NoDataFoundException.class)
    public ResponseEntity<ExceptionResponse> handleNoDataFoundException() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ExceptionResponse(
                Constants.NO_DATA_FOUND_EXCEPTION_MESSAGE, HttpStatus.NO_CONTENT.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleCategoryAlreadyExistsException() {
        return ResponseEntity.badRequest().body(new ExceptionResponse(
                Constants.CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE, HttpStatus.BAD_REQUEST.toString(), LocalDateTime.now()));
    }

    @ExceptionHandler(ElementNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleElementNotFoundException() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionResponse(
                Constants.ELEMENT_NOT_FOUND_EXCEPTION_MESSAGE, HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
    }
}
