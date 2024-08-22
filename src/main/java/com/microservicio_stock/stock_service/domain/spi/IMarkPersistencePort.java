package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.model.Mark;

import java.util.List;
import java.util.Optional;

public interface IMarkPersistencePort {
    void saveMark(Mark mark);
    Optional<Mark> getMarkById(Long id);
    List<Mark> getAllMarks(Integer page, Integer size, boolean ascOrderByName);
    Mark updateMark(Mark mark);
    void deleteMark(Long id);
}
