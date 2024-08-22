package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.model.Mark;

import java.util.List;
import java.util.Optional;

public interface IMarkServicePort {
    void saveMark(Mark mark);
    Optional<Mark> getMarkById(Long id);
    List<Mark> getAllMarks(Integer page, Integer size, Boolean ascOrderByName);
    Mark updateMark(Mark mark);
    void deleteMark(Long id);

}
