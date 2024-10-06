package tdr.task.botscrewtask.service;

import tdr.task.botscrewtask.model.entity.Department;
import tdr.task.botscrewtask.model.entity.Lector;

import java.util.List;

public interface DepartmentService {
    Lector getHeadByDepartmentName(String name);

    String formStatisticForDepartment(String departmentName);

    Double getAverageSalaryForDepartment(String departmentName);

    Integer countEmployeesForDepartment(String departmentName);

    List<Department> globalSearchByValue(String value);
}