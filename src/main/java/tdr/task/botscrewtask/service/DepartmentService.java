package tdr.task.botscrewtask.service;

import tdr.task.botscrewtask.model.dto.LectorDto;

public interface DepartmentService {
    LectorDto getHeadByDepartmentName(String name);

    String formStatisticForDepartment(String departmentName);

    Double getAverageSalaryForDepartment(String departmentName);

    Integer countEmployeesForDepartment(String departmentName);

}