package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IMarkEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IMarkRepository;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IMarkPersistencePort;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

@RequiredArgsConstructor
public class MarkAdapter implements IMarkPersistencePort {

    private final IMarkRepository markRepository;
    private final IMarkEntityMapper markEntityMapper;

    @Override
    public void saveMark(Mark mark) {
        if (markRepository.findByName(mark.getName()).isPresent()) {
            throw new ElementAlreadyExistsException();
        }
        markRepository.save(markEntityMapper.toEntity(mark));
    }



    @Override
    public List<Mark> getAllMarks(Integer page, Integer size, boolean ascOrderByName) {
        Sort sort = ascOrderByName ? Sort.by("name").ascending() : Sort.by("name").descending();
        Pageable pagination = PageRequest.of(page, size, sort);
        List<MarkEntity> marks = markRepository.findAll(pagination).getContent();
        if (marks.isEmpty()) {
            throw new NoDataFoundException();
        }
        return markEntityMapper.toModelList(marks);
    }

    @Override
    public long getTotalMarks() {
        return markRepository.count();
    }
}