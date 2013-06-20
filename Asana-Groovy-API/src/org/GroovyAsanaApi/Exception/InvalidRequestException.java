package org.GroovyAsanaApi.Exception;

public class InvalidRequestException extends AsanaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidRequestException(String message) {
		super(message);
	}
	public InvalidRequestException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
