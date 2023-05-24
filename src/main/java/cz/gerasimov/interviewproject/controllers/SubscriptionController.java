package cz.gerasimov.interviewproject.controllers;

import cz.gerasimov.interviewproject.dto.BasicResponseDto;
import cz.gerasimov.interviewproject.dto.BasicResponseEntity;
import cz.gerasimov.interviewproject.dto.requests.SubscriptionDto;
import cz.gerasimov.interviewproject.exceptions.ApplicationRuntimeException;
import cz.gerasimov.interviewproject.services.SubscriptionService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/subscription")
public class SubscriptionController {

	@NonNull
	private final SubscriptionService subscriptionService;

	@RequestMapping("/get/{id}")
	public ResponseEntity<BasicResponseEntity> getCustomer(@PathVariable Integer id) {
		var subscription = this.subscriptionService.getSubscription(id);
		return subscription.<ResponseEntity<BasicResponseEntity>>map(ResponseEntity::ok)
				.orElseGet(() ->
						new ResponseEntity<>(BasicResponseDto.builder()
						.status(false)
						.error(true)
						.errorMessage(String.format("Subscription [%d] not found", id))
						.build(),HttpStatus.NOT_FOUND)
				);
	}

	@RequestMapping("/create")
	public ResponseEntity<BasicResponseEntity> createQuotation(@RequestBody SubscriptionDto subscriptionDto) {
		BasicResponseDto.BasicResponseDtoBuilder responseBuilder;
		try {
			var subscription = this.subscriptionService.createSubscription(subscriptionDto);
			responseBuilder = BasicResponseDto.builder()
					.status(true)
					.error(false)
					.id(subscription.id());
		} catch (ApplicationRuntimeException e) {
			responseBuilder = BasicResponseDto.builder()
					.status(false)
					.error(true)
					.errorMessage(e.getClass().getSimpleName() + ": " + e.getMessage());
		}
		var response = responseBuilder.build();
		return new ResponseEntity<>(response, response.error() ? HttpStatus.BAD_REQUEST : HttpStatus.OK);
	}

}
