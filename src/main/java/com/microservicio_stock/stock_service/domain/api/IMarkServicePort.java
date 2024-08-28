package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Mark;

public interface IMarkServicePort {
    void saveMark(Mark mark);
    PagedResponse<Mark> getPagedMarks(Integer page, Integer size, Boolean ascOrderByName);

    Mark getMarkById(Long id);
}
