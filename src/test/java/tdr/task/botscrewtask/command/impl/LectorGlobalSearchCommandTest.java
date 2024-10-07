package tdr.task.botscrewtask.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.repository.LectorRepository;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class LectorGlobalSearchCommandTest {

    @Mock
    private LectorRepository lectorRepository;

    @InjectMocks
    private LectorGlobalSearchCommand lectorGlobalSearchCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testLectorGlobalSearchCommandWithResults() {
        String searchTerm = "van";
        Lector lector1 = new Lector();
        lector1.setFullName("Ivan Petrenko");
        Lector lector2 = new Lector();
        lector2.setFullName("Petro Ivanov");

        when(lectorRepository.globalSearchByValue(searchTerm)).thenReturn(List.of(lector1, lector2));

        CommandResult result = lectorGlobalSearchCommand.execute("Global search by " + searchTerm);

        assertThat(result).isNotNull();
        assertThat(result.asString()).isEqualTo("Global search results: Ivan Petrenko, Petro Ivanov");
        verify(lectorRepository, times(1)).globalSearchByValue(searchTerm);
    }

    @Test
    void testLectorGlobalSearchCommandNoResults() {
        String searchTerm = "nonexistent";

        when(lectorRepository.globalSearchByValue(searchTerm)).thenReturn(Collections.emptyList());

        CommandResult result = lectorGlobalSearchCommand.execute("Global search by " + searchTerm);

        assertThat(result).isNotNull();
        assertThat(result.asString()).isEqualTo("Global search results: No lecturers found for the search term: nonexistent");
        verify(lectorRepository, times(1)).globalSearchByValue(searchTerm);
    }
}
