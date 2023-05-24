package cz.gerasimov.interviewproject.mappers;

import cz.gerasimov.interviewproject.dto.requests.QuotationDto;
import cz.gerasimov.interviewproject.dto.responses.QuotationResponseDto;
import cz.gerasimov.interviewproject.models.Quotation;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Mapper(uses = {CustomerMapper.class}, componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface QuotationMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "customer", ignore = true)
	@Mapping(target = "subscriptions", ignore = true)
	Quotation quotationDtoToQuotation(QuotationDto quotationDto);

	QuotationResponseDto quotationToQuotationResponseDto(Quotation quotation);

}
