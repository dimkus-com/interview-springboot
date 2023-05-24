package cz.gerasimov.interviewproject.services;

import cz.gerasimov.interviewproject.dto.requests.CustomerDto;
import cz.gerasimov.interviewproject.dto.responses.CustomerResponseDto;
import cz.gerasimov.interviewproject.exceptions.CustomerNotFoundException;
import cz.gerasimov.interviewproject.models.Customer;
import java.util.Optional;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public interface CustomerService {

	Optional<CustomerResponseDto> getCustomer(Integer id);

	CustomerResponseDto createCustomer(CustomerDto customerDto);

	CustomerResponseDto updateCustomer(Integer customerId, CustomerDto customerDto);

	Customer getCustomerById(int id) throws CustomerNotFoundException;

}
