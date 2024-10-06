package tdr.task.botscrewtask.model.dto;

import lombok.EqualsAndHashCode;
import lombok.Value;
import tdr.task.botscrewtask.model.enums.Degree;

import java.io.Serializable;

/**
 * DTO for {@link tdr.task.botscrewtask.model.entity.Lector}
 */
@Value
@EqualsAndHashCode(callSuper = true)
public record LectorDto(Long id, String fullName, Degree degree, Double salary) implements Serializable {
}