package tdr.task.botscrewtask.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tdr.task.botscrewtask.model.dto.LectorDto;
import tdr.task.botscrewtask.model.entity.Lector;
import tdr.task.botscrewtask.model.enums.Degree;
import tdr.task.botscrewtask.model.mapper.LectorMapper;
import tdr.task.botscrewtask.repository.DepartmentRepository;
import tdr.task.botscrewtask.service.DepartmentService;

import java.util.Arrays;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final LectorMapper lectorMapper;

    @Override
    public LectorDto getHeadByDepartmentName(String name) {
        return lectorMapper.toDto(departmentRepository.getDepartmentByName(name).orElseThrow().getHead());
    }

    @Override
    public String formStatisticForDepartment(String departmentName) {
        return Arrays.stream(Degree.values())
                .map(degree -> String.format("%s - %d",
                        degree.name().toLowerCase().replace("_", " "),
                        departmentRepository.countLecturesByDepartmentName(departmentName, degree.ordinal())))
                .collect(Collectors.joining(", "));
    }

    @Override
    public Double getAverageSalaryForDepartment(String departmentName) {
        return departmentRepository.getAverageSalary(departmentName);
    }

    @Override
    public Integer countEmployeesForDepartment(String departmentName) {
        return departmentRepository.countEmployeesByDepartmentName(departmentName);
    }
}
