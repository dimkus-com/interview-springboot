package cz.gerasimov.interviewproject.dto.requests;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
public record SubscriptionDto(@Nullable Integer quotationId,
                              @Nullable ZonedDateTime startDate,
                              @Nullable ZonedDateTime validUntil) {
}
