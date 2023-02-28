package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PayerNotFoundException extends RuntimeException {
  public PayerNotFoundException() {
    super();
  }

  public PayerNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public PayerNotFoundException(final String message) {
    super(message);
  }

  public PayerNotFoundException(final Throwable cause) {
    super(cause);
  }
}
