package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.AddMarkRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.UpdateMarkRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkRequestMapper;
import com.microservicio_stock.stock_service.adapters.driving.http.mapper.mark.IMarkResponseMapper;
import com.microservicio_stock.stock_service.domain.api.IMarkServicePort;
import com.microservicio_stock.stock_service.domain.model.Mark;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/mark")
@RequiredArgsConstructor
public class MarkRestControllerAdapter {
    private final IMarkServicePort markServicePort;
    private final IMarkRequestMapper markRequestMapper;
    private final IMarkResponseMapper markResponseMapper;

    @PostMapping("/")
    public ResponseEntity<Void> addMark(@RequestBody AddMarkRequest request) {
        markServicePort.saveMark(markRequestMapper.addMarkRequestToMark(request));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MarkResponse> getMarkById(@PathVariable Long id) {
        Optional<Mark> mark = markServicePort.getMarkById(id);
        if (mark.isEmpty()) {
            throw new NoSuchElementException();
        }
        return ResponseEntity.ok(markResponseMapper.toMarkResponse(mark.get()));
    }

    @GetMapping("/")
    public ResponseEntity<List<MarkResponse>> getAllMarks(@RequestParam Integer page, @RequestParam Integer size, @RequestParam Boolean ascOrderByName) {
        return ResponseEntity.ok(markResponseMapper.toMarkResponseList(markServicePort.getAllMarks(page, size, ascOrderByName)));
    }

    @PutMapping("/")
    public ResponseEntity<MarkResponse> updateMark(@RequestBody UpdateMarkRequest request) {
        return ResponseEntity.ok(markResponseMapper.toMarkResponse(
                markServicePort.updateMark(markRequestMapper.updateMarkRequestToMark(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMark(@PathVariable Long id) {
        markServicePort.deleteMark(id);
        return ResponseEntity.noContent().build();
    }
}