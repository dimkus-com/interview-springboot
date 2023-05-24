package cz.gerasimov.interviewproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.gerasimov.interviewproject.dto.BasicResponseEntity;
import lombok.Builder;
import java.time.LocalDate;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record CustomerResponseDto(Integer id,
                                  String firstName,
                                  String lastName,
                                  String middleName,
                                  String email,
                                  String phoneNumber,
                                  LocalDate birthDate

) implements BasicResponseEntity { }
