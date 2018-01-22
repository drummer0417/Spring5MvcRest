package nl.androidappfactory.services;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8247948883903421637L;

	public ResourceNotFoundException() {

	}

	public ResourceNotFoundException(String message) {
		super(message);
	}

	public ResourceNotFoundException(Throwable cause) {
		super(cause);

	}

	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);

	}

	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStacktrace) {
		super(message, cause, enableSuppression, writableStacktrace);

	}

}
