package org.GroovyAsanaApi.Exception;

public class NotFoundException extends AsanaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NotFoundException(String message) {
		super(message);
	}
	public NotFoundException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
