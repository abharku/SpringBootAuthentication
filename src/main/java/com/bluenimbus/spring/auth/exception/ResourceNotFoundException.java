package com.bluenimbus.spring.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

  /**
	 * 
	 */
	private static final long serialVersionUID = 5682652420436177999L;

/**
   * Instantiates a new Resource not found exception.
   *
   * @param message the message
   */
  public ResourceNotFoundException(String message) {
    super(message);
  }
}
