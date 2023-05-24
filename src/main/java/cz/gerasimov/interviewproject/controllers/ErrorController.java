package cz.gerasimov.interviewproject.controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorViewResolver;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Spring Boot project for Interview
 * @author Dmitriy Gerasimov (www.gerasimov.cz)
 */

@Slf4j
@RestController
@RestControllerAdvice
public class ErrorController extends AbstractErrorController {

	public ErrorController(ErrorAttributes errorAttributes) {
		super(errorAttributes);
	}

	@Autowired
	public ErrorController(ErrorAttributes errorAttributes, List<ErrorViewResolver> errorViewResolvers) {
		super(errorAttributes, errorViewResolvers);
	}

	@RequestMapping("/error")
	public ResponseEntity<Object> handleError(HttpServletRequest request) {
		var errorCode = getHttpErrorCode(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		var httpStatus = getHttpStatus(errorCode);
		var map = new HashMap<String, Object>();
		map.put("error", httpStatus.name());
		map.put("error_code", errorCode);
		map.put("uri", String.valueOf(request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI)));
		log.error("API Request Error. Error code: {}, Path: [{}]", errorCode, map.get("uri"));
		return new ResponseEntity<>(map, httpStatus);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleException(Exception ex, HttpServletRequest request) {
		boolean connectionResetByPeer = (ex instanceof IOException && ex.getMessage().contains("Connection reset by peer")) || (ex instanceof ClientAbortException && ex.getMessage().contains("Broken pipe"));
		if (connectionResetByPeer) {
			// Connection was closed by client side, return nothing
			return null;
		}
		var errorCode = getHttpErrorCode(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE));
		var httpStatus = getHttpStatus(errorCode);
		var map = new HashMap<String, Object>();
		map.put("error", httpStatus.name());
		map.put("error_code", errorCode);
		map.put("uri", request.getServletPath());
		map.put("message", ex.getMessage());
		map.put("exception", ex.getClass().getCanonicalName());
		log.error("API Request Error. Error code: {}, Path [{}]: {}: {}", errorCode, map.get("servlet"), ex.getClass().getCanonicalName(), ex.getMessage(), ex);
		return new ResponseEntity<>(map, httpStatus);
	}

	private static int getHttpErrorCode(Object obj) {
		if (obj == null) return 500;
		try {
			return Integer.parseInt(String.valueOf(obj));
		} catch (NumberFormatException e) {
			return 500;
		}
	}

	private static HttpStatus getHttpStatus(int code) {
		var httpStatusResolve = HttpStatus.resolve(code);
		return httpStatusResolve == null ? HttpStatus.INTERNAL_SERVER_ERROR : httpStatusResolve;
	}

}
