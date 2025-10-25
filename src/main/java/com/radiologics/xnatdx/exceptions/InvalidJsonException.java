package com.radiologics.morpheus.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidJsonException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 13456345634563456L;

	public InvalidJsonException() {
        super();
    }

    public InvalidJsonException(final Throwable e) {
        super(e);
    }

    public InvalidJsonException(final String message) {
        super(message);
    }
}