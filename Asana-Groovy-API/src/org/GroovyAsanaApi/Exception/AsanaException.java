package org.GroovyAsanaApi.Exception;

public abstract class AsanaException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AsanaException(String message) {
		super(message);
	}

	public AsanaException(String message, Throwable throwable) {
		super(message, throwable);
	}
}
