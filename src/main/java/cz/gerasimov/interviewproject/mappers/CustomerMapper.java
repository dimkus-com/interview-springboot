package cz.gerasimov.interviewproject.mappers;

import cz.gerasimov.interviewproject.dto.requests.CustomerDto;
import cz.gerasimov.interviewproject.dto.responses.CustomerResponseDto;
import cz.gerasimov.interviewproject.models.Customer;
import org.mapstruct.*;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface CustomerMapper {

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "quotations", ignore = true)
	Customer customerDtoToCustomer(CustomerDto customerDto);

	@Mapping(target = "id", ignore = true)
	@Mapping(target = "quotations", ignore = true)
	Customer customerDtoToCustomer(@MappingTarget Customer customer, CustomerDto customerDto);

	CustomerResponseDto customerToCustomerResponseDto(Customer customer);

}
