package com.microservicio_stock.stock_service.domain.use_cases;

import com.microservicio_stock.stock_service.domain.model.Mark;
import com.microservicio_stock.stock_service.domain.spi.IMarkPersistencePort;
import com.microservicio_stock.stock_service.domain.util.PagedResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

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
    void saveMark_ShouldCallSaveMarkOnPersistencePort() {
        // Arrange
        Mark mark = new Mark(1L, "BrandName", "BrandDescription");

        // Act
        markUseCase.saveMark(mark);

        // Assert
        verify(markPersistencePort, times(1)).saveMark(mark);
    }

    @Test
    void getPagedMarks_ShouldReturnPagedMarksFromPersistencePort() {
        // Arrange
        int page = 1;
        int size = 10;
        boolean ascOrderByName = true;

        Mark mark1 = new Mark(1L, "BrandName1", "BrandDescription1");
        Mark mark2 = new Mark(2L, "BrandName2", "BrandDescription2");
        List<Mark> marks = Arrays.asList(mark1, mark2);

        PagedResponse<Mark> expectedResponse = new PagedResponse<>(
                marks, 1, 1, 2L, true
        );

        when(markPersistencePort.getPagedMarks(page, size, ascOrderByName)).thenReturn(expectedResponse);

        // Act
        PagedResponse<Mark> result = markUseCase.getPagedMarks(page, size, ascOrderByName);

        // Assert
        assertEquals(expectedResponse, result);
        verify(markPersistencePort, times(1)).getPagedMarks(page, size, ascOrderByName);
    }

    @Test
    void getMarkById_ShouldReturnMarkFromPersistencePort() {
        // Arrange
        Long id = 1L;
        Mark expectedMark = new Mark(id, "BrandName", "BrandDescription");

        when(markPersistencePort.getMarkById(id)).thenReturn(expectedMark);

        // Act
        Mark result = markUseCase.getMarkById(id);

        // Assert
        assertEquals(expectedMark, result);
        verify(markPersistencePort, times(1)).getMarkById(id);
    }
}
