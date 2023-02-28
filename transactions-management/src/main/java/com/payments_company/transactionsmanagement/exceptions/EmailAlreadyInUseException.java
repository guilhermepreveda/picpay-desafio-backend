package com.payments_company.transactionsmanagement.exceptions;

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