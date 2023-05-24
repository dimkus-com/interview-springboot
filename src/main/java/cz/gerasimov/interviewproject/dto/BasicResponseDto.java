package cz.gerasimov.interviewproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record BasicResponseDto(boolean status, boolean error, String errorMessage, Integer id) implements BasicResponseEntity {
}
