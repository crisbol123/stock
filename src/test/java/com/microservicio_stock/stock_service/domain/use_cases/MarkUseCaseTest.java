package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IMarkPersistencePort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class MarkUseCaseTest {

    @Mock
    private IMarkPersistencePort markPersistencePort;

    @InjectMocks
    private MarkUseCase markUseCase;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMark() {
        Mark mark = new Mark(1L, "Test Mark", "Description");

        markUseCase.saveMark(mark);

        verify(markPersistencePort, times(1)).saveMark(mark);
    }

    @Test
    void getMarkById() {
        Long markId = 1L;
        Optional<Mark> mark = Optional.of(new Mark(markId, "Test Mark", "Description"));

        when(markPersistencePort.getMarkById(markId)).thenReturn(mark);

        Optional<Mark> result = markUseCase.getMarkById(markId);

        assertEquals(mark, result);
        verify(markPersistencePort, times(1)).getMarkById(markId);
    }

    @Test
    void getAllMarks() {
        List<Mark> marks = Arrays.asList(
                new Mark(1L, "Mark 1", "Description 1"),
                new Mark(2L, "Mark 2", "Description 2")
        );

        when(markPersistencePort.getAllMarks(0, 10, true)).thenReturn(marks);

        List<Mark> result = markUseCase.getAllMarks(0, 10, true);

        assertEquals(marks.size(), result.size());
        verify(markPersistencePort, times(1)).getAllMarks(0, 10, true);
    }

    @Test
    void updateMark() {
        Mark mark = new Mark(1L, "Updated Mark", "Updated Description");

        when(markPersistencePort.updateMark(mark)).thenReturn(mark);

        Mark result = markUseCase.updateMark(mark);

        assertEquals(mark, result);
        verify(markPersistencePort, times(1)).updateMark(mark);
    }

    @Test
    void deleteMark() {
        Long markId = 1L;

        markUseCase.deleteMark(markId);

        verify(markPersistencePort, times(1)).deleteMark(markId);
    }
}