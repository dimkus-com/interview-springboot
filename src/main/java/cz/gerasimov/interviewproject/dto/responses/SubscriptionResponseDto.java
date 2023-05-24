package cz.gerasimov.interviewproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.gerasimov.interviewproject.dto.BasicResponseEntity;
import lombok.Builder;
import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record SubscriptionResponseDto(Integer id,
                                      QuotationResponseDto quotation,
                                      ZonedDateTime startDate,
                                      ZonedDateTime validUntil

) implements BasicResponseEntity { }
