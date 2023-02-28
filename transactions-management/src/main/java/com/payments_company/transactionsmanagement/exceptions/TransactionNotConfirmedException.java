package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TransactionNotConfirmedException extends RuntimeException {
  public TransactionNotConfirmedException() {
    super();
  }

  public TransactionNotConfirmedException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TransactionNotConfirmedException(final String message) {
    super(message);
  }

  public TransactionNotConfirmedException(final Throwable cause) {
    super(cause);
  }
}
