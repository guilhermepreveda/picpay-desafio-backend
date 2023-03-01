package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidUserTypeException extends RuntimeException {
  public InvalidUserTypeException() {
    super();
  }

  public InvalidUserTypeException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public InvalidUserTypeException(final String message) {
    super(message);
  }

  public InvalidUserTypeException(final Throwable cause) {
    super(cause);
  }
}
