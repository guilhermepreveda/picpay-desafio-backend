package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EmailAlreadyInUseException extends RuntimeException {

  public EmailAlreadyInUseException() {
    super();
  }

  public EmailAlreadyInUseException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public EmailAlreadyInUseException(final String message) {
    super(message);
  }

  public EmailAlreadyInUseException(final Throwable cause) {
    super(cause);
  }

}