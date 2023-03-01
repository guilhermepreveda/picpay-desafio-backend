package com.payments_company.transactionsmanagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CpfAlreadyInUseException extends RuntimeException {

  public CpfAlreadyInUseException() {
    super();
  }

  public CpfAlreadyInUseException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public CpfAlreadyInUseException(final String message) {
    super(message);
  }

  public CpfAlreadyInUseException(final Throwable cause) {
    super(cause);
  }

}