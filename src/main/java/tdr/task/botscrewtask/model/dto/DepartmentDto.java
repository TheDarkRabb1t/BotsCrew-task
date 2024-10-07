package tdr.task.botscrewtask.model.dto;

import tdr.task.botscrewtask.model.entity.Lector;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link tdr.task.botscrewtask.model.entity.Department}
 */
public record DepartmentDto(Long id, String name, String description, String location,
                            Set<Lector> lectors, LectorDto head) implements Serializable {
}