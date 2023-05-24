package cz.gerasimov.interviewproject.controllers;

import cz.gerasimov.interviewproject.dto.BasicResponseDto;
import cz.gerasimov.interviewproject.dto.BasicResponseEntity;
import cz.gerasimov.interviewproject.dto.requests.QuotationDto;
import cz.gerasimov.interviewproject.exceptions.ApplicationRuntimeException;
import cz.gerasimov.interviewproject.services.QuotationService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/quotation")
public class QuotationController {

	@NonNull
	private final QuotationService quotationService;

	@RequestMapping("/create")
	public ResponseEntity<BasicResponseEntity> createQuotation(@RequestBody QuotationDto quotationDto) {
		BasicResponseDto.BasicResponseDtoBuilder responseBuilder;
		try {
			var quotation = this.quotationService.createQuotation(quotationDto);
			responseBuilder = BasicResponseDto.builder()
					.status(true)
					.error(false)
					.id(quotation.id());
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
