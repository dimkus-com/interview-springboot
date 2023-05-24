package cz.gerasimov.interviewproject.exceptions;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public class ApplicationRuntimeException extends RuntimeException {

	public ApplicationRuntimeException() {
	}

	public ApplicationRuntimeException(String message) {
		super(message);
	}

	public ApplicationRuntimeException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApplicationRuntimeException(Throwable cause) {
		super(cause);
	}

	public ApplicationRuntimeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
