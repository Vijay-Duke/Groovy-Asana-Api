package org.GroovyAsanaApi.Exception;

public class ForbiddenException extends AsanaException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ForbiddenException(String message) {
		super(message);
	}
	public ForbiddenException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
