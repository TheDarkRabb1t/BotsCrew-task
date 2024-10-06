package tdr.task.botscrewtask.service;

import tdr.task.botscrewtask.model.entity.Lector;

public interface DepartmentService {
    Lector getHeadByDepartmentName(String name);

    String formStatisticForDepartment(String departmentName);

    Double getAverageSalaryForDepartment(String departmentName);

    Integer countEmployeesForDepartment(String departmentName);

}