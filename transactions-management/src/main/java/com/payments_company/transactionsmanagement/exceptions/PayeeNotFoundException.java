package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PayeeNotFoundException extends RuntimeException {
  public PayeeNotFoundException() {
    super();
  }

  public PayeeNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PayeeNotFoundException(final String message) {
    super(message);
  }

  public PayeeNotFoundException(final Throwable cause) {
    super(cause);
  }
}
