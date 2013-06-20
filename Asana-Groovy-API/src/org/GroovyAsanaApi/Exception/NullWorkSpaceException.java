package org.GroovyAsanaApi.Exception;

public class NullWorkSpaceException extends Exception{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NullWorkSpaceException(String message) {
	        super(message);
	    }
	 public NullWorkSpaceException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
