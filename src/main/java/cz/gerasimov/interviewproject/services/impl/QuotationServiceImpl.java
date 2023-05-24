package cz.gerasimov.interviewproject.services.impl;

import cz.gerasimov.interviewproject.dto.requests.QuotationDto;
import cz.gerasimov.interviewproject.dto.responses.QuotationResponseDto;
import cz.gerasimov.interviewproject.exceptions.CustomerNotFoundException;
import cz.gerasimov.interviewproject.exceptions.QuotationNotFoundException;
import cz.gerasimov.interviewproject.mappers.QuotationMapper;
import cz.gerasimov.interviewproject.models.Quotation;
import cz.gerasimov.interviewproject.repositories.QuotationRepository;
import cz.gerasimov.interviewproject.services.CustomerService;
import cz.gerasimov.interviewproject.services.QuotationService;
import jakarta.transaction.Transactional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class QuotationServiceImpl implements QuotationService {

	@NonNull
	private final QuotationRepository quotationRepository;
	@NonNull
	private final QuotationMapper quotationMapper;
	@NonNull
	private final CustomerService customerService;

	@Override
	@Transactional
	public QuotationResponseDto createQuotation(QuotationDto quotationDto) {
		if (quotationDto.customerId() == null) {
			throw new CustomerNotFoundException("You must define customer id.");
		}
		var customer = this.customerService.getCustomerById(quotationDto.customerId());
		var quotation = this.quotationMapper.quotationDtoToQuotation(quotationDto);
		quotation.setCustomer(customer);
		var result = this.quotationRepository.saveAndFlush(quotation);
		log.trace("QuotationServiceImpl.createQuotation: {}", result);
		return this.quotationMapper.quotationToQuotationResponseDto(result);
	}

	@Override
	@Transactional
	public Quotation getQuotationById(int id) throws QuotationNotFoundException {
		return this.quotationRepository.findById(id)
				.orElseThrow(() ->
						new QuotationNotFoundException(String.format("Quotation with id [%d] not found.", id))
				);
	}

}
