package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.model.Mark;

import java.util.List;


public interface IMarkPersistencePort {
    void saveMark(Mark mark);

    List<Mark> getAllMarks(Integer page, Integer size, boolean ascOrderByName);

    long getTotalMarks();
}
