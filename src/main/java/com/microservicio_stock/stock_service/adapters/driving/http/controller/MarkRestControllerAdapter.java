package com.microservicio_stock.stock_service.adapters.driving.http.controller;

import com.microservicio_stock.stock_service.adapters.driving.http.adapter.MarkAdapterHttp;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.PagedResponse;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.request.AddMarkRequest;
import com.microservicio_stock.stock_service.adapters.driving.http.dto.mark.response.MarkResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mark")
@RequiredArgsConstructor
@Tag(name = "Mark", description = "API for managing marks")
public class MarkRestControllerAdapter {

    private final MarkAdapterHttp markAdapterHttp;

    @Operation(summary = "Add a new mark", description = "Creates a new mark with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Mark created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping("/")
    public ResponseEntity<Void> addMark(@RequestBody AddMarkRequest request) {
        markAdapterHttp.saveMark(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Operation(summary = "Get all marks", description = "Retrieves all marks with pagination and sorting options")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marks retrieved successfully",
                    content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarkResponse.class))),
            @ApiResponse(responseCode = "404", description = "No marks found", content = @Content)
    })
    @GetMapping("/")
    public ResponseEntity<PagedResponse<MarkResponse>> getAllMarks(
            @Parameter(description = "Page number of the marks to retrieve", required = true)
            @RequestParam Integer page,
            @Parameter(description = "Number of marks per page", required = true)
            @RequestParam Integer size,
            @Parameter(description = "Sort marks by name in ascending order if true, descending if false", required = true)
            @RequestParam Boolean ascOrderByName) {
        return ResponseEntity.ok(markAdapterHttp.getAllMarks(page, size, ascOrderByName));
    }
}
