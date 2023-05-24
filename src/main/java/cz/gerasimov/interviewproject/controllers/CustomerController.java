package cz.gerasimov.interviewproject.controllers;

import cz.gerasimov.interviewproject.dto.BasicResponseEntity;
import cz.gerasimov.interviewproject.dto.requests.CustomerDto;
import cz.gerasimov.interviewproject.dto.BasicResponseDto;
import cz.gerasimov.interviewproject.exceptions.ApplicationRuntimeException;
import cz.gerasimov.interviewproject.services.CustomerService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {

	@NonNull
	private final CustomerService customerService;

	@RequestMapping("/get/{id}")
	public ResponseEntity<BasicResponseEntity> getCustomer(@PathVariable Integer id) {
		var customer = this.customerService.getCustomer(id);
		return customer.<ResponseEntity<BasicResponseEntity>>map(ResponseEntity::ok)
				.orElseGet(() ->
						new ResponseEntity<>(BasicResponseDto.builder()
								.status(false)
								.error(true)
								.errorMessage(String.format("Customer [%d] not found", id))
								.build(), HttpStatus.NOT_FOUND)
				);
	}

	@RequestMapping("/create")
	public ResponseEntity<BasicResponseEntity> createCustomer(@RequestBody CustomerDto customerDto) {
		var customer = this.customerService.createCustomer(customerDto);
		var response = BasicResponseDto.builder()
				.status(true)
				.error(false)
				.id(customer.id())
				.build();
		return ResponseEntity.ok(response);
	}

	@RequestMapping("/update/{id}")
	public ResponseEntity<BasicResponseEntity> updateCustomer(@RequestBody CustomerDto customerDto, @PathVariable Integer id) {
		BasicResponseDto.BasicResponseDtoBuilder responseBuilder;
		try {
			var customer = this.customerService.updateCustomer(id, customerDto);
			responseBuilder = BasicResponseDto.builder()
					.status(true)
					.error(false)
					.id(customer.id());
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
