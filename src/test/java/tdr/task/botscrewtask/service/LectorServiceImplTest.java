package tdr.task.botscrewtask.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.model.dto.LectorDto;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.model.enums.Degree;
import tdr.task.botscrewtask.model.mapper.LectorMapper;
import tdr.task.botscrewtask.repository.LectorRepository;
import tdr.task.botscrewtask.service.impl.LectorServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LectorServiceImplTest {

    @Mock
    private LectorRepository lectorRepository;

    @Mock
    private LectorMapper lectorMapper;

    @InjectMocks
    private LectorServiceImpl lectorService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGlobalSearchByValue_ShouldReturnMatchingLectors() {
        String searchValue = "john";

        Lector lector1 = new Lector();
        lector1.setFullName("John Doe");
        lector1.setDegree(Degree.ASSOCIATE_PROFESSOR);
        lector1.setSalary(5000.0);

        Lector lector2 = new Lector();
        lector2.setFullName("Johnny Smith");
        lector2.setDegree(Degree.PROFESSOR);
        lector2.setSalary(7000.0);

        LectorDto dto1 = new LectorDto(1L, "John Doe", Degree.ASSOCIATE_PROFESSOR, 5000.0);
        LectorDto dto2 = new LectorDto(2L, "Johnny Smith", Degree.PROFESSOR, 7000.0);

        when(lectorRepository.globalSearchByValue(searchValue))
                .thenReturn(Arrays.asList(lector1, lector2));

        when(lectorMapper.toDto(lector1)).thenReturn(dto1);
        when(lectorMapper.toDto(lector2)).thenReturn(dto2);

        String result = lectorService.globalSearchByValue(searchValue);

        assertNotNull(result);
        assertTrue(result.contains("John Doe"));
        assertTrue(result.contains("Johnny Smith"));

        verify(lectorRepository, times(1)).globalSearchByValue(searchValue);

        verify(lectorMapper, times(1)).toDto(lector1);
        verify(lectorMapper, times(1)).toDto(lector2);
    }

    @Test
    public void testGlobalSearchByValue_NoMatchesFound() {
        String searchValue = "lsa,d;las,d;asl,d;";
        when(lectorRepository.globalSearchByValue(searchValue)).thenReturn(List.of());

        String result = lectorService.globalSearchByValue(searchValue);
        assertEquals("No matching lectors found.", result);

        verify(lectorRepository, times(1)).globalSearchByValue(searchValue);
        verifyNoInteractions(lectorMapper);
    }
}
