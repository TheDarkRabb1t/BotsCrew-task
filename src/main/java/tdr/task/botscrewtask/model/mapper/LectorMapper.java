package tdr.task.botscrewtask.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import tdr.task.botscrewtask.model.dto.LectorDto;
import tdr.task.botscrewtask.model.entity.Lector;

@Mapper
public interface LectorMapper {
    LectorDto toDto(Lector lector);

    Lector toEntity(LectorDto lectorDto);

    void update(@MappingTarget Lector lector, LectorDto lectorDto);
}
