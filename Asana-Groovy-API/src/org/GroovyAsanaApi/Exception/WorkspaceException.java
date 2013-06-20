package org.GroovyAsanaApi.Exception;

public class WorkspaceException extends Exception {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public WorkspaceException(String message) {
	        super(message);
	    }
	 public WorkspaceException(String message, Throwable throwable) {
	        super(message, throwable);
	    }

}
