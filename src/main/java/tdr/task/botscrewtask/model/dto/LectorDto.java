package tdr.task.botscrewtask.model.dto;

import tdr.task.botscrewtask.model.enums.Degree;

import java.io.Serializable;

/**
 * DTO for {@link tdr.task.botscrewtask.model.entity.Lector}
 */
public record LectorDto(Long id, String fullName, Degree degree, Double salary) implements Serializable {
}