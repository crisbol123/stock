package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IMarkEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IMarkRepository;
import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MarkAdapterTest {

    @InjectMocks
    private MarkAdapter markAdapter;

    @Mock
    private IMarkRepository markRepository;

    @Mock
    private IMarkEntityMapper markEntityMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMark_ShouldSaveMark() {
        // Arrange
        Mark mark = new Mark(1L, "Mark Name", "Mark Description");
        MarkEntity markEntity = new MarkEntity(1L, "Mark Name", "Mark Description");

        when(markRepository.findIdByName(mark.getName())).thenReturn(Optional.empty());
        when(markEntityMapper.toEntity(mark)).thenReturn(markEntity);

        // Act
        markAdapter.saveMark(mark);

        // Assert
        verify(markRepository, times(1)).save(markEntity);
    }

    @Test
    void saveMark_ShouldThrowElementAlreadyExistsException() {
        // Arrange
        Mark mark = new Mark(1L, "Mark Name", "Mark Description");
        when(markRepository.findIdByName(mark.getName())).thenReturn(Optional.of(anyLong()));

        // Act & Assert
        assertThrows(ElementAlreadyExistsException.class, () -> markAdapter.saveMark(mark));
    }

    @Test
    void getPagedMarks_ShouldReturnPagedMarks() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));
        Page<MarkEntity> markEntitiesPage = mock(Page.class);
        when(markRepository.findAll(pageable)).thenReturn(markEntitiesPage);
        when(markEntitiesPage.isEmpty()).thenReturn(false);

        // Prepare dummy data
        MarkEntity markEntity = new MarkEntity(1L, "Mark Name", "Mark Description");
        List<MarkEntity> markEntityList = List.of(markEntity);
        when(markEntitiesPage.getContent()).thenReturn(markEntityList);
        when(markEntitiesPage.isLast()).thenReturn(false);
        when(markEntitiesPage.getNumber()).thenReturn(page);
        when(markEntitiesPage.getTotalPages()).thenReturn(5);
        when(markEntitiesPage.getTotalElements()).thenReturn(50L);

        Mark mark = new Mark(1L, "Mark Name", "Mark Description");
        PagedResponse<Mark> expectedResponse = new PagedResponse<>(
                List.of(mark),
                page,
                5,
                50L,
                false
        );

        when(markEntityMapper.toPagedModel(markEntitiesPage, markEntitiesPage.isLast(), markEntitiesPage.getNumber()))
                .thenReturn(expectedResponse);

        // Act
        PagedResponse<Mark> result = markAdapter.getPagedMarks(page, size, ascOrderByName);

        // Assert
        assertNotNull(result);
        assertEquals(expectedResponse.getContent(), result.getContent());
        assertEquals(expectedResponse.getCurrentPage(), result.getCurrentPage());
        assertEquals(expectedResponse.getTotalPages(), result.getTotalPages());
        assertEquals(expectedResponse.getTotalElements(), result.getTotalElements());
        assertEquals(expectedResponse.isLastPage(), result.isLastPage());
        verify(markRepository, times(1)).findAll(pageable);
        verify(markEntityMapper, times(1)).toPagedModel(markEntitiesPage, markEntitiesPage.isLast(), markEntitiesPage.getNumber());
    }

    @Test
    void getPagedMarks_NoDataFoundException() {
        // Arrange
        Integer page = 0;
        Integer size = 10;
        boolean ascOrderByName = true;
        Pageable pageable = PageRequest.of(page, size, Sort.by(ascOrderByName ? Sort.Direction.ASC : Sort.Direction.DESC, "name"));
        when(markRepository.findAll(pageable)).thenReturn(Page.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> markAdapter.getPagedMarks(page, size, ascOrderByName));
    }

    @Test
    void getMarkById_ShouldReturnMark() {
        // Arrange
        Long id = 1L;
        MarkEntity markEntity = new MarkEntity(id, "Mark Name", "Mark Description");
        Mark mark = new Mark(id, "Mark Name", "Mark Description");

        when(markRepository.findById(id)).thenReturn(Optional.of(markEntity));
        when(markEntityMapper.toModel(markEntity)).thenReturn(mark);

        // Act
        Mark result = markAdapter.getMarkById(id);

        // Assert
        assertNotNull(result);
        assertEquals(mark.getId(), result.getId());
        assertEquals(mark.getName(), result.getName());
        assertEquals(mark.getDescription(), result.getDescription());
        verify(markRepository, times(1)).findById(id);
        verify(markEntityMapper, times(1)).toModel(markEntity);
    }

    @Test
    void getMarkById_NoDataFoundException() {
        // Arrange
        Long id = 1L;
        when(markRepository.findById(id)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> markAdapter.getMarkById(id));
    }
}