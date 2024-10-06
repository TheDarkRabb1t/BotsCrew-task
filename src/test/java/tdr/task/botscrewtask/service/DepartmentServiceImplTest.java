package tdr.task.botscrewtask.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tdr.task.botscrewtask.model.entity.Department;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.model.enums.Degree;
import tdr.task.botscrewtask.model.mapper.DepartmentMapper;
import tdr.task.botscrewtask.repository.DepartmentRepository;
import tdr.task.botscrewtask.service.impl.DepartmentServiceImpl;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetHeadByDepartmentName_ShouldReturnDepartmentHead() {
        String departmentName = "Computer Science";
        Lector headLector = new Lector();
        headLector.setFullName("John Doe");
        headLector.setDegree(Degree.PROFESSOR);
        headLector.setSalary(9000.0);
        headLector.setCreated(Instant.now());
        headLector.setUpdated(Instant.now());

        Department department = new Department();
        department.setName(departmentName);
        department.setHead(headLector);

        when(departmentRepository.getDepartmentByName(departmentName)).thenReturn(department);

        Lector result = departmentService.getHeadByDepartmentName(departmentName);

        assertNotNull(result);
        assertEquals("John Doe", result.getFullName());
        verify(departmentRepository, times(1)).getDepartmentByName(departmentName);
    }

    @Test
    public void testFormStatisticForDepartment_ShouldReturnStatistics() {
        String departmentName = "Computer Science";

        when(departmentRepository.countLecturesByDepartmentName(departmentName, Degree.ASSISTANT.ordinal()))
                .thenReturn(10);
        when(departmentRepository.countLecturesByDepartmentName(departmentName, Degree.ASSOCIATE_PROFESSOR.ordinal()))
                .thenReturn(5);
        when(departmentRepository.countLecturesByDepartmentName(departmentName, Degree.PROFESSOR.ordinal()))
                .thenReturn(3);

        String result = departmentService.formStatisticForDepartment(departmentName);

        assertEquals("assistants - 10, associate professors - 5, professors - 3", result);
        verify(departmentRepository, times(1))
                .countLecturesByDepartmentName(departmentName, Degree.ASSISTANT.ordinal());
        verify(departmentRepository, times(1))
                .countLecturesByDepartmentName(departmentName, Degree.ASSOCIATE_PROFESSOR.ordinal());
        verify(departmentRepository, times(1))
                .countLecturesByDepartmentName(departmentName, Degree.PROFESSOR.ordinal());
    }

    @Test
    public void testGetAverageSalaryForDepartment_ShouldReturnAverageSalary() {
        String departmentName = "Computer Science";
        Double expectedAverageSalary = 6500.00;

        when(departmentRepository.getAverageSalary(departmentName)).thenReturn(expectedAverageSalary);

        Double result = departmentService.getAverageSalaryForDepartment(departmentName);

        assertNotNull(result);
        assertEquals(expectedAverageSalary, result);
        verify(departmentRepository, times(1)).getAverageSalary(departmentName);
    }

    @Test
    public void testCountEmployeesForDepartment_ShouldReturnEmployeeCount() {
        String departmentName = "Computer Science";
        Integer expectedEmployeeCount = 25;

        when(departmentRepository.countEmployeesByDepartmentName(departmentName)).thenReturn(expectedEmployeeCount);

        Integer result = departmentService.countEmployeesForDepartment(departmentName);

        assertEquals(expectedEmployeeCount, result);
        verify(departmentRepository, times(1)).countEmployeesByDepartmentName(departmentName);
    }
}
