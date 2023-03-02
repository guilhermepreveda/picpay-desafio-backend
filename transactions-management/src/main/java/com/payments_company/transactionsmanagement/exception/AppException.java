package com.payments_company.transactionsmanagement.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

  private HttpStatus statusCode;

  public AppException(final String message, final HttpStatus statusCode) {
    super(message);
    this.statusCode = statusCode;
  }

  public HttpStatus getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(final HttpStatus statusCode) {
    this.statusCode = statusCode;
  }

}