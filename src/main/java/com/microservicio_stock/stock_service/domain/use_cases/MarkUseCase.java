package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IMarkPersistencePort;

import java.util.List;
import java.util.Optional;

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
    public Optional<Mark> getMarkById(Long id) {
        return markPersistencePort.getMarkById(id);
    }

    @Override
    public List<Mark> getAllMarks(Integer page, Integer size, Boolean ascOrderByName) {
        return markPersistencePort.getAllMarks(page, size, ascOrderByName);
    }

    @Override
    public Mark updateMark(Mark mark) {
        return markPersistencePort.updateMark(mark);
    }

    @Override
    public void deleteMark(Long id) {
        markPersistencePort.deleteMark(id);
    }
}