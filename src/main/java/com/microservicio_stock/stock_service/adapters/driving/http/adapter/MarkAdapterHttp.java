package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.AddMarkRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.FindAllMarksRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkResponseMapper;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class MarkAdapterHttp {
    private final IMarkServicePort markServicePort;
    private final IMarkRequestMapper markRequestMapper;
    private final IMarkResponseMapper markResponseMapper;

    public void saveMark(AddMarkRequest addMarkRequest) {
        markServicePort.saveMark(markRequestMapper.addMarkRequestToMark(addMarkRequest));
    }
    public PagedResponse<MarkResponse> getPagedMarks(FindAllMarksRequest findAllMarksRequest) {
        PagedResponse<Mark> pagedResponse = markServicePort.getPagedMarks(findAllMarksRequest.getPage(), findAllMarksRequest.getSize(), findAllMarksRequest.isAscOrderByName());
        return markResponseMapper.toMarkResponsePagedResponse(pagedResponse);
    }
}
