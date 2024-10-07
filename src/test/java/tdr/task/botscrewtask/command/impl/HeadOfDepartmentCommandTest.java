package tdr.task.botscrewtask.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.model.entity.Department;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class HeadOfDepartmentCommandTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private HeadOfDepartmentCommand headOfDepartmentCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testHeadOfDepartmentCommand() {
        Lector lector = new Lector();
        lector.setFullName("Dr. John Doe");
        String departmentName = "Computer Science";
        Department department = new Department();
        department.setName(departmentName);
        department.setHead(lector);

        when(departmentRepository.getDepartmentByName(departmentName)).thenReturn(Optional.of(department));

        CommandResult result = headOfDepartmentCommand.execute("Who is head of department " + departmentName);

        assertThat(result).isNotNull();
        assertThat(result.asString()).isEqualTo("Head of Computer Science department is Dr. John Doe");
        verify(departmentRepository, times(1)).getDepartmentByName(departmentName);
    }
}
