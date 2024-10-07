package tdr.task.botscrewtask.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import tdr.task.botscrewtask.command.CommandProcessor;
import tdr.task.botscrewtask.model.entity.Department;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.model.enums.Degree;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
public class DepartmentRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private DepartmentRepository departmentRepository;
    @MockBean
    private CommandProcessor commandProcessor;
    private Department department;

    @BeforeEach
    void setUp() {
        Lector lector = new Lector();
        lector.setFullName("Dr. John Doe");
        lector.setDegree(Degree.PROFESSOR);
        lector.setSalary(80000.0);
        lector.setCreated(Instant.now());
        lector.setUpdated(Instant.now());
        entityManager.persist(lector);


        department = new Department();
        department.setName("Computer Science");
        department.setDescription("Department of Computer Science");
        department.setLocation("Building A");
        department.setHead(lector);
        department.setLectors(new HashSet<>());

        department.getLectors().add(lector);

        entityManager.persist(department);
        entityManager.flush();
    }

    @Test
    void testGetDepartmentByName() {
        Department found = departmentRepository.getDepartmentByName("Computer Science").get();

        assertThat(found).isNotNull();
        assertThat(found.getName()).isEqualTo("Computer Science");
    }

    @Test
    void testGetAverageSalary() {
        Lector lector = new Lector();
        lector.setFullName("John");
        lector.setSalary(50000.0);
        lector.setDegree(Degree.values()[1]);
        entityManager.persist(lector);

        department.getLectors().add(lector);
        entityManager.persist(department);
        entityManager.flush();

        Double averageSalary = departmentRepository.getAverageSalary("Computer Science");

        assertThat(averageSalary).isEqualTo(65000);
    }

    @Test
    void testCountLecturesByDepartmentName() {
        Lector lector = new Lector();
        lector.setFullName("John");
        lector.setSalary(50000.0);
        lector.setDegree(Degree.values()[1]);
        entityManager.persist(lector);

        department.getLectors().add(lector);
        entityManager.persist(department);
        entityManager.flush();

        Integer count = departmentRepository.countLecturesByDepartmentName("Computer Science", 1);

        assertThat(count).isEqualTo(1);
    }

    @Test
    void testCountEmployeesByDepartmentName() {
        Integer count = departmentRepository.countEmployeesByDepartmentName("Computer Science");

        assertThat(count).isEqualTo(1);
    }

    @Test
    void testGlobalSearchByValue() {
        List<Department> departmentList = departmentRepository.globalSearchByValue("Computer");

        assertThat(departmentList).isNotNull();
        assertThat(departmentList.size()).isGreaterThan(0);
        Department foundDepartment = departmentList.getFirst();
        assertThat(foundDepartment).isEqualTo(department);
    }
}
