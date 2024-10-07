package tdr.task.botscrewtask.command.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.command.CommandResult;
import tdr.task.botscrewtask.model.enums.Degree;
import tdr.task.botscrewtask.repository.DepartmentRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ShowStatisticsCommandTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private ShowStatisticsCommand showStatisticsCommand;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testShowStatisticsCommand() {
        String departmentName = "Biology";
        when(departmentRepository.countLecturesByDepartmentName(departmentName, Degree.ASSISTANT.ordinal())).thenReturn(10);
        when(departmentRepository.countLecturesByDepartmentName(departmentName, Degree.ASSOCIATE_PROFESSOR.ordinal())).thenReturn(20);
        when(departmentRepository.countLecturesByDepartmentName(departmentName, Degree.PROFESSOR.ordinal())).thenReturn(30);

        CommandResult result = showStatisticsCommand.execute("Show " + departmentName + " statistics.");

        assertThat(result).isNotNull();
        assertThat(result.asString()).isEqualTo("assistants - 10\nassociate professors - 20\nprofessors - 30");
        verify(departmentRepository, times(1)).countLecturesByDepartmentName(departmentName, Degree.ASSISTANT.ordinal());
        verify(departmentRepository, times(1)).countLecturesByDepartmentName(departmentName, Degree.ASSOCIATE_PROFESSOR.ordinal());
        verify(departmentRepository, times(1)).countLecturesByDepartmentName(departmentName, Degree.PROFESSOR.ordinal());
    }
}
