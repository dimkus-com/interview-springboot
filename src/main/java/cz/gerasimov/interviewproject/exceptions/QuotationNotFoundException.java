package cz.gerasimov.interviewproject.exceptions;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public class QuotationNotFoundException extends ApplicationRuntimeException {

	public QuotationNotFoundException() {
	}

	public QuotationNotFoundException(String message) {
		super(message);
	}

	public QuotationNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public QuotationNotFoundException(Throwable cause) {
		super(cause);
	}

	public QuotationNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
