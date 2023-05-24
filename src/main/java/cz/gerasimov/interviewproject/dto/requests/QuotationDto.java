package cz.gerasimov.interviewproject.dto.requests;

import jakarta.annotation.Nullable;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
public record QuotationDto(@Nullable ZonedDateTime beginingOfInsurance,
                           @Nullable BigDecimal insuredAmount,
                           @Nullable ZonedDateTime dateOfSigningMortgage,
                           @Nullable Integer customerId) {
}