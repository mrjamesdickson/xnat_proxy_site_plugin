package com.radiologics.morpheus.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotUniqueException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 13456345634563456L;

	public NotUniqueException() {
        super();
    }

    public NotUniqueException(final Throwable e) {
        super(e);
    }

    public NotUniqueException(final String message) {
        super(message);
    }
}