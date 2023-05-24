package cz.gerasimov.interviewproject.mappers;

import cz.gerasimov.interviewproject.dto.requests.SubscriptionDto;
import cz.gerasimov.interviewproject.dto.responses.SubscriptionResponseDto;
import cz.gerasimov.interviewproject.models.Subscription;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.MappingConstants;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Mapper(uses = {QuotationMapper.class}, componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface SubscriptionMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "quotation", ignore = true)
	Subscription subscriptionDtoToSubscription(SubscriptionDto subscriptionDto);

	SubscriptionResponseDto subscriptionToSubscriptionResponseDto(Subscription subscription);

}
