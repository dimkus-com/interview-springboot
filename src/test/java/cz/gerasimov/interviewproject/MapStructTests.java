package cz.gerasimov.interviewproject;

import cz.gerasimov.interviewproject.dto.requests.CustomerDto;
import cz.gerasimov.interviewproject.dto.requests.QuotationDto;
import cz.gerasimov.interviewproject.dto.requests.SubscriptionDto;
import cz.gerasimov.interviewproject.mappers.CustomerMapper;
import cz.gerasimov.interviewproject.mappers.QuotationMapper;
import cz.gerasimov.interviewproject.mappers.SubscriptionMapper;
import cz.gerasimov.interviewproject.models.Customer;
import cz.gerasimov.interviewproject.models.Quotation;
import cz.gerasimov.interviewproject.models.Subscription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@SpringBootTest
class MapStructTests {

	@Autowired
	CustomerMapper customerMapper;

	@Autowired
	QuotationMapper quotationMapper;

	@Autowired
	SubscriptionMapper subscriptionMapper;
	
	@Test
	@DisplayName("CustomerMapper")
	void customerMapper() {
		var customerDto = buildCustomerDto();
		var customer = customerMapper.customerDtoToCustomer(customerDto);
		Assertions.assertEquals(LocalDate.parse(StubConstants.CUSTOMER_DAY).toString(), customer.getBirthDate().toString());
		Assertions.assertEquals(StubConstants.CUSTOMER_FIRST_NAME, customer.getFirstName());
		Assertions.assertEquals(StubConstants.CUSTOMER_LAST_NAME, customer.getLastName());
		Assertions.assertEquals(StubConstants.CUSTOMER_MIDDLE_NAME, customer.getMiddleName());
		Assertions.assertEquals(StubConstants.CUSTOMER_EMAIL, customer.getEmail());
		customer.setId(StubConstants.CUSTOMER_ID);
		var responseDto = customerMapper.customerToCustomerResponseDto(customer);
		Assertions.assertEquals(StubConstants.CUSTOMER_ID, responseDto.id());
		Assertions.assertEquals(LocalDate.parse(StubConstants.CUSTOMER_DAY).toString(), responseDto.birthDate().toString());
		Assertions.assertEquals(StubConstants.CUSTOMER_FIRST_NAME, responseDto.firstName());
		Assertions.assertEquals(StubConstants.CUSTOMER_LAST_NAME, responseDto.lastName());
		Assertions.assertEquals(StubConstants.CUSTOMER_MIDDLE_NAME, responseDto.middleName());
		Assertions.assertEquals(StubConstants.CUSTOMER_EMAIL, responseDto.email());
		var customer2 = customerMapper.customerDtoToCustomer(buildCustomer(), customerDto);
		Assertions.assertEquals(StubConstants.CUSTOMER_ID2, customer2.getId());
		Assertions.assertEquals(LocalDate.parse(StubConstants.CUSTOMER_DAY).toString(), customer2.getBirthDate().toString());
		Assertions.assertEquals(StubConstants.CUSTOMER_FIRST_NAME, customer2.getFirstName());
		Assertions.assertEquals(StubConstants.CUSTOMER_LAST_NAME, customer2.getLastName());
		Assertions.assertEquals(StubConstants.CUSTOMER_MIDDLE_NAME, customer2.getMiddleName());
		Assertions.assertEquals(StubConstants.CUSTOMER_EMAIL, customer2.getEmail());
	}

	@Test
	@DisplayName("QuotationMapper")
	void quotationMapper() {
		var quotationDto = buildQuotationDto();
		var quotation = quotationMapper.quotationDtoToQuotation(quotationDto);
		Assertions.assertEquals(StubConstants.QUOTATION_DATE_INSURANCE_EXPECTED, quotation.getBeginingOfInsurance().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.QUOTATION_DATE_MORTGAGE_EXPECTED, quotation.getDateOfSigningMortgage().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.BIG_DECIMAL_STRING, quotation.getInsuredAmount().toPlainString());
		Assertions.assertNull(quotation.getId());
		Assertions.assertNull(quotation.getCustomer());
		Assertions.assertNull(quotation.getSubscriptions());
		var responseDto = quotationMapper.quotationToQuotationResponseDto(buildQuotation());
		Assertions.assertEquals(StubConstants.QUOTATION_ID, responseDto.id());
		Assertions.assertEquals(StubConstants.CUSTOMER_ID2, responseDto.customer().id());
		Assertions.assertEquals(StubConstants.QUOTATION_DATE_INSURANCE_EXPECTED, responseDto.beginingOfInsurance().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.QUOTATION_DATE_MORTGAGE_EXPECTED, responseDto.dateOfSigningMortgage().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.BIG_DECIMAL_STRING, responseDto.insuredAmount().toPlainString());
	}

	@Test
	@DisplayName("SubscriptionMapper")
	void subscriptionMapper() {
		var subscriptionDto = buildSubscriptionDto();
		var subscription = subscriptionMapper.subscriptionDtoToSubscription(subscriptionDto);
		Assertions.assertNull(subscription.getId());
		Assertions.assertNull(subscription.getQuotation());
		Assertions.assertEquals(StubConstants.SUBSCRIPTION_START_DATE_EXPECTED, subscription.getStartDate().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.SUBSCRIPTION_VALID_UNTIL_EXPECTED, subscription.getValidUntil().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		var responseDto = subscriptionMapper.subscriptionToSubscriptionResponseDto(buildSubscription());
		Assertions.assertEquals(StubConstants.SUBSCRIPTION_START_DATE_EXPECTED, responseDto.startDate().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.SUBSCRIPTION_VALID_UNTIL_EXPECTED, responseDto.validUntil().withZoneSameInstant(StubConstants.ZONE_UTC).toString());
		Assertions.assertEquals(StubConstants.SUBSCRIPTION_ID, responseDto.id());
		Assertions.assertEquals(StubConstants.QUOTATION_ID, responseDto.quotation().id());
		Assertions.assertEquals(StubConstants.CUSTOMER_ID2, responseDto.quotation().customer().id());
	}

	CustomerDto buildCustomerDto() {
		return CustomerDto.builder()
				.firstName(StubConstants.CUSTOMER_FIRST_NAME)
				.lastName(StubConstants.CUSTOMER_LAST_NAME)
				.email(StubConstants.CUSTOMER_EMAIL)
				.middleName(StubConstants.CUSTOMER_MIDDLE_NAME)
				.birthDate(LocalDate.parse(StubConstants.CUSTOMER_DAY))
				.build();
	}

	QuotationDto buildQuotationDto() {
		return QuotationDto.builder()
				.customerId(StubConstants.CUSTOMER_ID)
				.insuredAmount(StubConstants.BIG_DECIMAL_VALUE)
				.beginingOfInsurance(ZonedDateTime.parse(StubConstants.QUOTATION_DATE_INSURANCE_GIVEN))
				.dateOfSigningMortgage(ZonedDateTime.parse(StubConstants.QUOTATION_DATE_MORTGAGE_GIVEN))
				.build();
	}

	SubscriptionDto buildSubscriptionDto() {
		return SubscriptionDto.builder()
				.quotationId(StubConstants.QUOTATION_ID)
				.startDate(ZonedDateTime.parse(StubConstants.SUBSCRIPTION_START_DATE_GIVEN))
				.validUntil(ZonedDateTime.parse(StubConstants.SUBSCRIPTION_VALID_UNTIL_GIVEN))
				.build();
	}

	Customer buildCustomer() {
		return Customer.builder()
				.id(StubConstants.CUSTOMER_ID2)
				.firstName(StubConstants.CUSTOMER_FIRST_NAME)
				.lastName(StubConstants.CUSTOMER_LAST_NAME)
				.email(StubConstants.CUSTOMER_EMAIL2)
				.middleName(StubConstants.CUSTOMER_MIDDLE_NAME2)
				.birthDate(LocalDate.parse(StubConstants.CUSTOMER_BIRTH_DAY))
				.build();
	}

	Quotation buildQuotation() {
		return Quotation.builder()
				.id(StubConstants.QUOTATION_ID)
				.customer(buildCustomer())
				.insuredAmount(StubConstants.BIG_DECIMAL_VALUE)
				.beginingOfInsurance(ZonedDateTime.parse(StubConstants.QUOTATION_DATE_INSURANCE_GIVEN))
				.dateOfSigningMortgage(ZonedDateTime.parse(StubConstants.QUOTATION_DATE_MORTGAGE_GIVEN))
				.build();
	}

	Subscription buildSubscription() {
		return Subscription.builder()
				.id(StubConstants.SUBSCRIPTION_ID)
				.quotation(buildQuotation())
				.startDate(ZonedDateTime.parse(StubConstants.SUBSCRIPTION_START_DATE_GIVEN))
				.validUntil(ZonedDateTime.parse(StubConstants.SUBSCRIPTION_VALID_UNTIL_GIVEN))
				.build();
	}

}
