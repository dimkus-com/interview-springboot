package cz.gerasimov.interviewproject.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import cz.gerasimov.interviewproject.dto.BasicResponseEntity;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record QuotationResponseDto(Integer id,
                                   ZonedDateTime beginingOfInsurance,
                                   BigDecimal insuredAmount,
                                   ZonedDateTime dateOfSigningMortgage,
                                   CustomerResponseDto customer

) implements BasicResponseEntity { }
