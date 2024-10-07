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

public class ShowAverageSalaryForDepartmentCommandTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ShowAverageSalaryForDepartmentCommand showAverageSalaryForDepartmentCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowAverageSalaryForDepartmentCommand() {
        String departmentName = "Math";
        double avgSalary = 5000.0;
        when(departmentRepository.getAverageSalary(departmentName)).thenReturn(avgSalary);

        CommandResult result = showAverageSalaryForDepartmentCommand.execute("Show the average salary for the department " + departmentName);

        assertThat(result).isNotNull();
        assertThat(result.asString()).isEqualTo(String.format("The average salary of %s is %.2f", departmentName, avgSalary));
        verify(departmentRepository, times(1)).getAverageSalary(departmentName);
    }
}
