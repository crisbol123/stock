package com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark;

import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import com.microservicio_stock.stock_service.domain.model.Mark;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IMarkResponseMapper {

    MarkResponse toMarkResponse(Mark mark);

    List<MarkResponse> toMarkResponseList(List<Mark> marks);
    PagedResponse<MarkResponse> toMarkResponsePagedResponse(PagedResponse<Mark> pagedResponse);

}