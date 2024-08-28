package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.domain.model.Mark;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarkEntityMapper {

    Mark toModel(MarkEntity markEntity);

    MarkEntity toEntity(Mark mark);

    List<Mark> toModelList(List<MarkEntity> markEntities);

    @Mapping(target = "lastPage", source = "isLast")
    @Mapping(target = "currentPage", source = "number")
    PagedResponse<Mark> toPagedModel(Page<MarkEntity> page, boolean isLast, int number);
}