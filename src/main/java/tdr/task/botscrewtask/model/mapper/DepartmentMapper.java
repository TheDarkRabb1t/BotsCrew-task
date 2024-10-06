package tdr.task.botscrewtask.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tdr.task.botscrewtask.model.dto.DepartmentDto;
import tdr.task.botscrewtask.model.entity.Department;

@Mapper
public interface DepartmentMapper {
    DepartmentDto toDto(Department department);

    Department toEntity(DepartmentDto departmentDto);

    void update(@MappingTarget Department department, DepartmentDto departmentDto);
}
