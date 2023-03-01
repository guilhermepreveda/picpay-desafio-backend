package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class TransactionNotFoundException extends RuntimeException {
  public TransactionNotFoundException() {
    super();
  }

  public TransactionNotFoundException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TransactionNotFoundException(final String message) {
    super(message);
  }

  public TransactionNotFoundException(final Throwable cause) {
    super(cause);
  }
}
