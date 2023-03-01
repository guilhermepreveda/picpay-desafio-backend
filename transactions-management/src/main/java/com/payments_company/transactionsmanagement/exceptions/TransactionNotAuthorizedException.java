package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class TransactionNotAuthorizedException extends RuntimeException {
  public TransactionNotAuthorizedException() {
    super();
  }

  public TransactionNotAuthorizedException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public TransactionNotAuthorizedException(final String message) {
    super(message);
  }

  public TransactionNotAuthorizedException(final Throwable cause) {
    super(cause);
  }
}
