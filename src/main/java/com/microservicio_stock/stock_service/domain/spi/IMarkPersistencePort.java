package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;

import java.util.List;


public interface IMarkPersistencePort {
    void saveMark(Mark mark);

   PagedResponse<Mark> getPagedMarks(Integer page, Integer size, boolean ascOrderByName);

    Mark getMarkById(Long id);
}
