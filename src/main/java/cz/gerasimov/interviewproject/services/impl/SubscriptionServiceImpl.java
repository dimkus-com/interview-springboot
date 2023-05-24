package cz.gerasimov.interviewproject.services.impl;

import cz.gerasimov.interviewproject.dto.requests.SubscriptionDto;
import cz.gerasimov.interviewproject.dto.responses.SubscriptionResponseDto;
import cz.gerasimov.interviewproject.exceptions.QuotationNotFoundException;
import cz.gerasimov.interviewproject.exceptions.SubscriptionNotFoundException;
import cz.gerasimov.interviewproject.mappers.SubscriptionMapper;
import cz.gerasimov.interviewproject.models.Subscription;
import cz.gerasimov.interviewproject.repositories.SubscriptionRepository;
import cz.gerasimov.interviewproject.services.QuotationService;
import cz.gerasimov.interviewproject.services.SubscriptionService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

	@NonNull
	private final SubscriptionRepository subscriptionRepository;
	@NonNull
	private final SubscriptionMapper subscriptionMapper;
	@NonNull
	private final QuotationService quotationService;

	@Override
	@Transactional
	public Optional<SubscriptionResponseDto> getSubscription(Integer id) {
		try {
			var subscription = this.getSubscriptionById(id);
			return Optional.ofNullable(this.subscriptionMapper.subscriptionToSubscriptionResponseDto(subscription));
		} catch (SubscriptionNotFoundException e) {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public SubscriptionResponseDto createSubscription(SubscriptionDto subscriptionDto) {
		if (subscriptionDto.quotationId() == null) {
			throw new QuotationNotFoundException("You must define quotation id.");
		}
		var quotation = this.quotationService.getQuotationById(subscriptionDto.quotationId());
		var subscription = this.subscriptionMapper.subscriptionDtoToSubscription(subscriptionDto);
		subscription.setQuotation(quotation);
		var result = this.subscriptionRepository.saveAndFlush(subscription);
		log.trace("SubscriptionServiceImpl.createSubscription: {}", result);
		return this.subscriptionMapper.subscriptionToSubscriptionResponseDto(result);
	}

	@Override
	@Transactional
	public Subscription getSubscriptionById(int id) throws SubscriptionNotFoundException {
		return this.subscriptionRepository.findById(id)
				.orElseThrow(() ->
						new SubscriptionNotFoundException(String.format("Subscription with id [%d] not found.", id))
				);
	}

}
