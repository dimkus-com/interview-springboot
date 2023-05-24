package cz.gerasimov.interviewproject.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.time.ZonedDateTime;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Configuration
public class AppConfig {

	@Bean("jackson2ObjectMapperBuilder")
	Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder() {
		return new Jackson2ObjectMapperBuilder()
				.modulesToInstall(new JavaTimeModule())
				.serializerByType(ZonedDateTime.class, new ZonedDateTimeIsoSerializer())
				.serializationInclusion(JsonInclude.Include.NON_EMPTY)
				.postConfigurer(om -> {
					om.configure(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS, true);
					om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
					om.enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN);
				});
	}

	@Bean("objectMapper")
	ObjectMapper objectMapper(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder){
		return jackson2ObjectMapperBuilder.build();
	}

}
