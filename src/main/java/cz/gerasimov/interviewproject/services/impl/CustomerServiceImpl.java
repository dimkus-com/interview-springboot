package cz.gerasimov.interviewproject.services.impl;

import cz.gerasimov.interviewproject.dto.requests.CustomerDto;
import cz.gerasimov.interviewproject.dto.responses.CustomerResponseDto;
import cz.gerasimov.interviewproject.exceptions.CustomerNotFoundException;
import cz.gerasimov.interviewproject.exceptions.CustomerUpdateException;
import cz.gerasimov.interviewproject.mappers.CustomerMapper;
import cz.gerasimov.interviewproject.models.Customer;
import cz.gerasimov.interviewproject.repositories.CustomerRepository;
import cz.gerasimov.interviewproject.services.CustomerService;
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
public class CustomerServiceImpl implements CustomerService {

	@NonNull
	private final CustomerRepository customerRepository;
	@NonNull
	private final CustomerMapper customerMapper;

	@Override
	@Transactional
	public Optional<CustomerResponseDto> getCustomer(Integer id) {
		try {
			var customer = this.getCustomerById(id);
			return Optional.ofNullable(this.customerMapper.customerToCustomerResponseDto(customer));
		} catch (CustomerNotFoundException e) {
			return Optional.empty();
		}
	}

	@Override
	@Transactional
	public CustomerResponseDto createCustomer(CustomerDto customerDto) {
		var customer = this.customerMapper.customerDtoToCustomer(customerDto);
		var result = this.customerRepository.saveAndFlush(customer);
		log.trace("CustomerServiceImpl.createCustomer: {}", result);
		return this.customerMapper.customerToCustomerResponseDto(result);
	}

	@Override
	@Transactional
	public CustomerResponseDto updateCustomer(Integer customerId, CustomerDto customerDto) {
		if (customerId == null) {
			throw new CustomerUpdateException("You must define id for update customer attributes.");
		}
		var customer = this.getCustomerById(customerId);
		var result = this.customerRepository.saveAndFlush(this.customerMapper.customerDtoToCustomer(customer, customerDto));
		log.trace("CustomerServiceImpl.updateCustomer: {}", result);
		return this.customerMapper.customerToCustomerResponseDto(result);
	}

	@Override
	@Transactional
	public Customer getCustomerById(int id) throws CustomerNotFoundException {
		var customer = this.customerRepository.findById(id);
		return customer.orElseThrow(() ->
						new CustomerNotFoundException(String.format("Customer with id [%d] not found.", id))
				);
	}

}
