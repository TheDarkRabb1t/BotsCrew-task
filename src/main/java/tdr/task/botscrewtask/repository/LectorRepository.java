package tdr.task.botscrewtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tdr.task.botscrewtask.model.entity.Lector;

import java.util.List;

@Repository
public interface LectorRepository extends JpaRepository<Lector, Long> {
    @Query(value = """
            select *
            from lector l
            where l.full_name ILIKE concat('%', :value, '%');
            """, nativeQuery = true)
    List<Lector> globalSearchByValue(@Param("value") String value);
}
