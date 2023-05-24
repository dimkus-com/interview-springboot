package cz.gerasimov.interviewproject.exceptions;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public class CustomerUpdateException extends ApplicationRuntimeException {

	public CustomerUpdateException() {
	}

	public CustomerUpdateException(String message) {
		super(message);
	}

	public CustomerUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	public CustomerUpdateException(Throwable cause) {
		super(cause);
	}

	public CustomerUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
