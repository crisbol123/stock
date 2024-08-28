package com.microservicio_stock.stock_service.domain.spi;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Mark;


public interface IMarkPersistencePort {
    void saveMark(Mark mark);

   PagedResponse<Mark> getPagedMarks(Integer page, Integer size, boolean ascOrderByName);

    Mark getMarkById(Long id);
}
