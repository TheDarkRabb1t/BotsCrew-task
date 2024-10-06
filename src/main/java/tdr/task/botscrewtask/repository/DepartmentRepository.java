package tdr.task.botscrewtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tdr.task.botscrewtask.model.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
