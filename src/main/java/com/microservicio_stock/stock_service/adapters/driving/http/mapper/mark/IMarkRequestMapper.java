package com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.AddMarkRequest;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Mark;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface IMarkRequestMapper {

    Mark addMarkRequestToMark(AddMarkRequest request);

    @Named("idToMark")
    default Mark idToMark(Long id, @Context IMarkServicePort markServicePort) {
        return markServicePort.getMarkById(id);

    }
}
