package org.GroovyAsanaApi.Exception;

public class RateLimitEnforcedException extends AsanaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RateLimitEnforcedException(String message) {
		super(message);
	}

	public RateLimitEnforcedException(String message, Throwable throwable) {
		super(message, throwable);
	}

}
