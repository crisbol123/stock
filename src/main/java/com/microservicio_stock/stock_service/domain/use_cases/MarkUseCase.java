package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IMarkPersistencePort;


public class MarkUseCase implements IMarkServicePort {

    private final IMarkPersistencePort markPersistencePort;

    public MarkUseCase(IMarkPersistencePort markPersistencePort) {
        this.markPersistencePort = markPersistencePort;
    }


    @Override
    public void saveMark(Mark mark) {
        markPersistencePort.saveMark(mark);
    }

    @Override
    public PagedResponse<Mark> getPagedMarks(Integer page, Integer size, Boolean ascOrderByName) {
        return markPersistencePort.getPagedMarks(page, size, ascOrderByName);
    }

    @Override
    public Mark getMarkById(Long id) {
        return markPersistencePort.getMarkById(id);
    }
   }
