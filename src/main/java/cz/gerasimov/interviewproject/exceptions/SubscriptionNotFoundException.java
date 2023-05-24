package cz.gerasimov.interviewproject.exceptions;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

public class SubscriptionNotFoundException extends ApplicationRuntimeException {

	public SubscriptionNotFoundException() {
	}

	public SubscriptionNotFoundException(String message) {
		super(message);
	}

	public SubscriptionNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public SubscriptionNotFoundException(Throwable cause) {
		super(cause);
	}

	public SubscriptionNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
