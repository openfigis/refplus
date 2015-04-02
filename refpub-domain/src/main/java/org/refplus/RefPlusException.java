package org.refplus;

/**
 * Generic vme runtime exception. Vme so far does not have checked exceptions, the idea behind is that checked
 * exceptions create a lot of ugly boilerplate code, and more important, all code should be tested so that exception
 * will not occur runtime.
 * 
 * 
 * 
 * 
 * 
 * @author Erik van Ingen
 * 
 */
public class RefPlusException extends RuntimeException {

	public RefPlusException(Exception e) {
		super(e);
	}

	public RefPlusException(String message) {
		super(message);
	}
	
	public RefPlusException(String message, Exception e){
		super(message, e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1359907165992410885L;

}
