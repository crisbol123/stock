package com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.adapter;

import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.entity.MarkEntity;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.ElementAlreadyExistsException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.exception.NoDataFoundException;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.mapper.IMarkEntityMapper;
import com.microservicio_stock.stock_service.adapters.driven.jpa.mysql.repository.IMarkRepository;
import com.microservicio_stock.stock_service.domain.model.Mark;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class MarkAdapterTest {

    @Mock
    private IMarkRepository markRepository;

    @Mock
    private IMarkEntityMapper markEntityMapper;

    @InjectMocks
    private MarkAdapter markAdapter;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveMark_Success() {
        // Arrange
        Mark mark = new Mark(1L, "Samsung", "celular");
        MarkEntity markEntity = new MarkEntity();
        markEntity.setName("Samsung");

        when(markRepository.findByName("Samsung")).thenReturn(Optional.empty());
        when(markEntityMapper.toEntity(any(Mark.class))).thenReturn(markEntity);

        // Act
        markAdapter.saveMark(mark);

        // Assert
        verify(markRepository, times(1)).save(markEntity);
    }

    @Test
    void testSaveMark_AlreadyExists() {
        // Arrange
        Mark mark = new Mark(1L, "Samsung", "celular");

        when(markRepository.findByName("Samsung")).thenReturn(Optional.of(new MarkEntity()));

        // Act & Assert
        assertThrows(ElementAlreadyExistsException.class, () -> {
            markAdapter.saveMark(mark);
        });

        verify(markRepository, never()).save(any(MarkEntity.class));
    }

    @Test
    void testGetAllMarks_Success() {
        // Arrange
        MarkEntity markEntity = new MarkEntity();
        markEntity.setName("Samsung");
        List<MarkEntity> markEntities = List.of(markEntity);

        Page<MarkEntity> page = new PageImpl<>(markEntities);
        when(markRepository.findAll(any(Pageable.class))).thenReturn(page);

        Mark mark = new Mark(1L, "Samsung", "celular");
        when(markEntityMapper.toModelList(markEntities)).thenReturn(List.of(mark));

        // Act
        List<Mark> result = markAdapter.getPagedMarks(0, 10, true);

        // Assert
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Samsung", result.get(0).getName());
    }

    @Test
    void testGetAllMarks_NoDataFound() {
        // Arrange
        Page<MarkEntity> emptyPage = new PageImpl<>(Collections.emptyList());
        when(markRepository.findAll(any(Pageable.class))).thenReturn(emptyPage);

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> {
            markAdapter.getPagedMarks(0, 10, true);
        });

        verify(markEntityMapper, never()).toModelList(anyList());
    }

    @Test
    void testGetTotalMarks() {
        // Arrange
        when(markRepository.count()).thenReturn(5L);

        // Act
        long result = markAdapter.getTotalMarks();

        // Assert
        assertEquals(5L, result);
        verify(markRepository, times(1)).count();
    }
}
