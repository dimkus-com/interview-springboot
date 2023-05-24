package cz.gerasimov.interviewproject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import cz.gerasimov.interviewproject.dto.responses.QuotationResponseDto;
import cz.gerasimov.interviewproject.dto.responses.SubscriptionResponseDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@SpringBootTest
class ApplicationTests {

	@Autowired
	ObjectMapper objectMapper;

	@Test
	@DisplayName("ZonedDateTime marshall/unmarshal")
	void zonedDateTimeFormat() throws JsonProcessingException {
		var given = "{\"startDate\":\"2023-05-24T15:37:16Z\"}";
		var json = objectMapper.readValue(given, SubscriptionResponseDto.class);
		var expected = objectMapper.writeValueAsString(json);
		Assertions.assertEquals(given, expected);
	}

	@Test
	@DisplayName("BigDecimal marshall/unmarshal")
	void bigDecimalFormat() throws JsonProcessingException {
		var given = "{\"insuredAmount\":9999.9999999999}";
		var json = objectMapper.readValue(given, QuotationResponseDto.class);
		var expected = objectMapper.writeValueAsString(json);
		Assertions.assertEquals(given, expected);
	}

}
