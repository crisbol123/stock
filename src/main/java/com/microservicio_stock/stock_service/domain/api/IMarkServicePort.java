package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.model.Mark;

import java.util.List;

public interface IMarkServicePort {
    void saveMark(Mark mark);
    List<Mark> getAllMarks(Integer page, Integer size, Boolean ascOrderByName);

    long getTotalMarks();
}
