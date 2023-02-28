package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class BalanceNotSufficientException extends RuntimeException {
  public BalanceNotSufficientException() {
    super();
  }

  public BalanceNotSufficientException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public BalanceNotSufficientException(final String message) {
    super(message);
  }

  public BalanceNotSufficientException(final Throwable cause) {
    super(cause);
  }
}
