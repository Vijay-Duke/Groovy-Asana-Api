package org.GroovyAsanaApi.Exception;

public class NullParentException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NullParentException(String message) {
	        super(message);
	    }
	 public NullParentException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
