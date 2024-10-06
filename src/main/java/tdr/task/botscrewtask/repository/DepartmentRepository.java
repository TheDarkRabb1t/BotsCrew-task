package tdr.task.botscrewtask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tdr.task.botscrewtask.model.entity.Department;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Department getDepartmentByName(String name);

    @Query(value = """
            select avg(l.salary)
            from department d
                     join public.department_lectors dl on d.id = dl.department_id
                     join lector l on dl.lectors_id = l.id
            where d.name = :name;
            """, nativeQuery = true)
    Double getAverageSalary(@Param("name") String departmentName);

    @Query(value = """
            select count(*)
            from department d
                     join public.department_lectors dl on d.id = dl.department_id
                     join lector l on dl.lectors_id = l.id
            where d.name = :name and l.degree=:degreeInt;
            """, nativeQuery = true)
    Integer countLecturesByDepartmentName(@Param("name") String departmentName, Integer degreeInt);

    @Query(value = """
            select count(*)
            from department d
                     join public.department_lectors dl on d.id = dl.department_id
            where d.name = :name;
            """, nativeQuery = true)
    Integer countEmployeesByDepartmentName(@Param("name") String departmentName);

    @Query(value = """
            select *
            from department d
            where d.name ILIKE concat('%', :value, '%');
            """, nativeQuery = true)
    List<Department> globalSearchByValue(@Param("value") String value);

}
