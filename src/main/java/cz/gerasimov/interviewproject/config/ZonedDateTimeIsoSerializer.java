package cz.gerasimov.interviewproject.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public class ZonedDateTimeIsoSerializer extends StdSerializer<ZonedDateTime> {

	public static final String ISO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss'Z'";
	public static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ofPattern(ISO_PATTERN);

	public ZonedDateTimeIsoSerializer() {
		super(ZonedDateTime.class);
	}

	@Override
	public void serialize(ZonedDateTime dateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
		jsonGenerator.writeString(ISO_FORMATTER.format(dateTime));
	}

}