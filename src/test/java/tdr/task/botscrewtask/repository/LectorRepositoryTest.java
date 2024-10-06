package tdr.task.botscrewtask.repository;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.model.enums.Degree;

import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class LectorRepositoryTest {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private LectorRepository lectorRepository;

    private Lector lector;

    @BeforeEach
    void setUp() {
        lector = new Lector();
        lector.setFullName("Dr. John Doe");
        lector.setDegree(Degree.PROFESSOR);
        lector.setSalary(80000.0);
        lector.setCreated(Instant.now());
        lector.setUpdated(Instant.now());
        entityManager.persist(lector);

        entityManager.flush();
    }

    @Test
    void testGlobalSearchByValue() {
        List<Lector> lectorList = lectorRepository.globalSearchByValue("john");

        assertThat(lectorList).isNotNull();
        assertThat(lectorList.size()).isGreaterThan(0);
        Lector foundLector = lectorList.getFirst();
        assertThat(foundLector).isEqualTo(lector);
    }
}
