package tdr.task.botscrewtask.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ShowEmployeeCountForDepartmentCommandTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ShowEmployeeCountForDepartmentCommand showEmployeeCountForDepartmentCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowEmployeeCountForDepartmentCommand() {
        String departmentName = "Physics";
        when(departmentRepository.countEmployeesByDepartmentName(departmentName)).thenReturn(100);

        CommandResult result = showEmployeeCountForDepartmentCommand.execute("Show count of employee for " + departmentName);

        assertThat(result).isNotNull();
        assertThat(result.asString()).isEqualTo("Count of employees for " + departmentName + " is 100");
        verify(departmentRepository, times(1)).countEmployeesByDepartmentName(departmentName);
    }
}
