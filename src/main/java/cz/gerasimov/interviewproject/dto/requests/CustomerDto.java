package cz.gerasimov.interviewproject.dto.requests;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.LocalDate;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
public record CustomerDto(@Nullable String firstName,
                          @Nullable String lastName,
                          @Nullable String middleName,
                          @Nullable String email,
                          @Nullable String phoneNumber,
                          @Nullable LocalDate birthDate) {
}
