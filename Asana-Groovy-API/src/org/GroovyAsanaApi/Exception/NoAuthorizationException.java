package org.GroovyAsanaApi.Exception;

public class NoAuthorizationException extends AsanaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoAuthorizationException(String message) {
		super(message);
	}
	public NoAuthorizationException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
