package cz.gerasimov.interviewproject.services;

import cz.gerasimov.interviewproject.dto.requests.QuotationDto;
import cz.gerasimov.interviewproject.dto.responses.QuotationResponseDto;
import cz.gerasimov.interviewproject.exceptions.QuotationNotFoundException;
import cz.gerasimov.interviewproject.models.Quotation;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public interface QuotationService {

	QuotationResponseDto createQuotation(QuotationDto quotationDto);

	Quotation getQuotationById(int id) throws QuotationNotFoundException;

}
