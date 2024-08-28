package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.MarkAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.AddMarkRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.FindAllMarksRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MarkRestControllerAdapterTest {

    @Mock
    private MarkAdapterHttp markAdapterHttp;

    @InjectMocks
    private MarkRestControllerAdapter markRestControllerAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddMark() {
        AddMarkRequest addMarkRequest = new AddMarkRequest();
        doNothing().when(markAdapterHttp).saveMark(any(AddMarkRequest.class));

        ResponseEntity<Void> response = markRestControllerAdapter.addMark(addMarkRequest);

        verify(markAdapterHttp, times(1)).saveMark(addMarkRequest);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }

    @Test
    void testGetAllMarks() {
        FindAllMarksRequest findAllMarksRequest = new FindAllMarksRequest(0,1,true);
        PagedResponse<MarkResponse> pagedResponse = new PagedResponse<>(
                Collections.emptyList(), 1, 1, 0L, true);
        when(markAdapterHttp.getPagedMarks(any(FindAllMarksRequest.class))).thenReturn(pagedResponse);

        ResponseEntity<PagedResponse<MarkResponse>> response = markRestControllerAdapter.getAllMarks(findAllMarksRequest);

        verify(markAdapterHttp, times(1)).getPagedMarks(findAllMarksRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pagedResponse, response.getBody());
    }
}
