package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.domain.model.Mark;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarkEntityMapper {

    Mark toModel(MarkEntity markEntity);

    MarkEntity toEntity(Mark mark);

    List<Mark> toModelList(List<MarkEntity> markEntities);

    List<MarkEntity> toEntityList(List<Mark> marks);
}