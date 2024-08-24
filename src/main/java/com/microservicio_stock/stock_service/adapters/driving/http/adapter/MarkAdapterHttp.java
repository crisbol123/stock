package com.microservicio_stock.stock_service.adapters.driving.http.adapter;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.AddMarkRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkResponseMapper;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Mark;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@AllArgsConstructor
public class MarkAdapterHttp {
    private final IMarkServicePort markServicePort;
    private final IMarkRequestMapper markRequestMapper;
    private final IMarkResponseMapper markResponseMapper;

    public void saveMark(AddMarkRequest addMarkRequest) {
        markServicePort.saveMark(markRequestMapper.addMarkRequestToMark(addMarkRequest));
    }

    public PagedResponse<MarkResponse> getAllMarks(Integer page, Integer size, Boolean ascOrderByName) {
        List<Mark> marks = markServicePort.getAllMarks(page, size, ascOrderByName);
        long totalElements = markServicePort.getTotalMarks();
        int totalPages = (int) Math.ceil((double) totalElements / size);
        boolean lastPage = page >= totalPages - 1;

        List<MarkResponse> markResponses = markResponseMapper.toMarkResponseList(marks);

        return new PagedResponse<>(
            markResponses,
            page,
            totalPages,
            totalElements,
            lastPage,
            ascOrderByName
        );
    }
}
