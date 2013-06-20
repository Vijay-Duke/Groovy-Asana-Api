package org.GroovyAsanaApi.Exception;

public class NullTaskNameException extends Exception {
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NullTaskNameException(String message) {
	        super(message);
	    }
	 public NullTaskNameException(String message, Throwable throwable) {
	        super(message, throwable);
	    }
}
