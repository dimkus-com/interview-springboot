package cz.gerasimov.interviewproject.services;

import cz.gerasimov.interviewproject.dto.requests.SubscriptionDto;
import cz.gerasimov.interviewproject.dto.responses.SubscriptionResponseDto;
import cz.gerasimov.interviewproject.exceptions.SubscriptionNotFoundException;
import cz.gerasimov.interviewproject.models.Subscription;
import java.util.Optional;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public interface SubscriptionService {

	Optional<SubscriptionResponseDto> getSubscription(Integer id);
	SubscriptionResponseDto createSubscription(SubscriptionDto subscriptionDto);
	Subscription getSubscriptionById(int id) throws SubscriptionNotFoundException;

}
