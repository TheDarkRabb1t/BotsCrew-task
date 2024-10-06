package tdr.task.botscrewtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdr.task.botscrewtask.model.entity.Lector;

public interface LectorRepository extends JpaRepository<Lector, Long> {
}
