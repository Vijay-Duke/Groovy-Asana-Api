package org.GroovyAsanaApi.Exception;

public class NullTaskIdException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NullTaskIdException(String message) {
	        super(message);
	    }
	 public NullTaskIdException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
