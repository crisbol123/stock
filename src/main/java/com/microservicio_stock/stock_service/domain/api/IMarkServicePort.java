package com.microservicio_stock.stock_service.domain.api;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Category;
import com.microservicio_stock.stock_service.domain.model.Mark;

import java.util.Calendar;
import java.util.List;

public interface IMarkServicePort {
    void saveMark(Mark mark);
    PagedResponse<Mark> getPagedMarks(Integer page, Integer size, Boolean ascOrderByName);

    Mark getMarkById(Long id);
}
