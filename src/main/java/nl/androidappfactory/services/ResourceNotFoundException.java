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

}
