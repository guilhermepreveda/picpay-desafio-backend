package com.payments_company.transactionsmanagement.web.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.payments_company.transactionsmanagement.web.utils.GenericResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @Autowired
  private MessageSource messages;

  public GlobalExceptionHandler() {
    super();
  }

  @Override
  protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatusCode status,
      WebRequest request) {
    final BindingResult result = ex.getBindingResult();
    final GenericResponse response = new GenericResponse(result.getAllErrors(),
        "Invalid" + StringUtils.capitalize(result.getObjectName()));

    return handleExceptionInternal(ex, response, new HttpHeaders(), HttpStatus.BAD_REQUEST,
        request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    final BindingResult result = ex.getBindingResult();
    final GenericResponse response = new GenericResponse(result.getAllErrors(),
        "Invalid" + StringUtils.capitalize(result.getObjectName()));

    return handleExceptionInternal(ex, response, new HttpHeaders(),
        HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ AppException.class })
  public ResponseEntity<Object> handleAppException(final AppException ex, final WebRequest request) {
    final GenericResponse response = new GenericResponse(
        messages.getMessage("message." + ex.getMessage(), null, request.getLocale()),
        StringUtils.capitalize(ex.getMessage()));

    return handleExceptionInternal(ex, response, new HttpHeaders(),
        ex.getStatusCode(),
        request);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
    final GenericResponse response = new GenericResponse(
        messages.getMessage("message.error", null, request.getLocale()), "InternalError");

    return new ResponseEntity<>(response, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
