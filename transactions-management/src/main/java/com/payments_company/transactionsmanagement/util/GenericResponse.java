package com.payments_company.transactionsmanagement.util;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class GenericResponse {

  private String message;
  private String error;

  public GenericResponse(final String message) {
    super();
    this.message = message;
  }

  public GenericResponse(final String message, final String error) {
    super();
    this.message = message;
    this.error = error;
  }

  public GenericResponse(List<ObjectError> allErrors, String error) {
    this.error = error;

    String stringList = allErrors.stream().map(err -> {
      if (err instanceof FieldError) {
        return "{\"" + ((FieldError) err).getField() + "\":\"" + err.getDefaultMessage() + "\"}";
      }

      return "{\"" + err.getObjectName() + "\":\"" + err.getDefaultMessage() + "\"}";
    }).collect(Collectors.joining(","));

    this.message = "[" + stringList + "]";
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(final String message) {
    this.message = message;
  }

  public String getError() {
    return error;
  }

  public void setError(final String error) {
    this.error = error;
  }

}
