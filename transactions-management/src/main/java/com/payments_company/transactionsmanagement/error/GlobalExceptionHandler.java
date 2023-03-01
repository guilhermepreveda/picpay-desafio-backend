package com.payments_company.transactionsmanagement.error;

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

import com.payments_company.transactionsmanagement.exceptions.BalanceNotSufficientException;
import com.payments_company.transactionsmanagement.exceptions.CpfAlreadyInUseException;
import com.payments_company.transactionsmanagement.exceptions.EmailAlreadyInUseException;
import com.payments_company.transactionsmanagement.exceptions.InvalidUserTypeException;
import com.payments_company.transactionsmanagement.exceptions.PayeeNotFoundException;
import com.payments_company.transactionsmanagement.exceptions.PayerNotFoundException;
import com.payments_company.transactionsmanagement.exceptions.TransactionNotAuthorizedException;
import com.payments_company.transactionsmanagement.exceptions.TransactionNotConfirmedException;
import com.payments_company.transactionsmanagement.exceptions.TransactionNotFoundException;
import com.payments_company.transactionsmanagement.exceptions.UserNotFoundException;
import com.payments_company.transactionsmanagement.util.GenericResponse;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @Autowired
  private MessageSource messages;

  public GlobalExceptionHandler() {
    super();
  }

  @Override
  protected ResponseEntity<Object> handleBindException(
      BindException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    final BindingResult result = ex.getBindingResult();
    final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(),
        "Invalid" + StringUtils.capitalize(result.getObjectName()));

    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    final BindingResult result = ex.getBindingResult();
    final GenericResponse bodyOfResponse = new GenericResponse(result.getAllErrors(),
        "Invalid" + StringUtils.capitalize(result.getObjectName()));
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
  }

  @ExceptionHandler({ UserNotFoundException.class })
  public ResponseEntity<Object> handleUserNotFound(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.userNotFound", null, request.getLocale()), "UserNotFound");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({ TransactionNotFoundException.class })
  public ResponseEntity<Object> handleTransactionNotFound(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.transactionNotFound", null, request.getLocale()), "TransactionNotFound");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({ PayerNotFoundException.class })
  public ResponseEntity<Object> handlePayerNotFound(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.payerNotFound", null, request.getLocale()), "PayerNotFound");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({ PayeeNotFoundException.class })
  public ResponseEntity<Object> handlePayeeNotFound(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.payeeNotFound", null, request.getLocale()), "PayeeNotFound");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
  }

  @ExceptionHandler({ BalanceNotSufficientException.class })
  public ResponseEntity<Object> handleBalanceNotSufficient(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.balanceNotSufficient", null, request.getLocale()), "BalanceNotSufficient");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
  }

  @ExceptionHandler({ InvalidUserTypeException.class })
  public ResponseEntity<Object> handleInvalidUserType(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.invalidUserType", null, request.getLocale()), "InvalidUserType");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.FORBIDDEN, request);
  }

  @ExceptionHandler({ TransactionNotAuthorizedException.class })
  public ResponseEntity<Object> handleTransactionNotAuthorized(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.transactionNotAuthorized", null, request.getLocale()), "TransactionNotAuthorized");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({ TransactionNotConfirmedException.class })
  public ResponseEntity<Object> handleTransactionNotConfirmed(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.transactionNotConfirmed", null, request.getLocale()), "TransactionNotConfirmed");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({ EmailAlreadyInUseException.class })
  public ResponseEntity<Object> handleEmailAlreadyInUseException(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.emailAlreadyInUseException", null, request.getLocale()), "EmailAlreadyInUse");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({ CpfAlreadyInUseException.class })
  public ResponseEntity<Object> handleCpfAlreadyInUse(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.cpfAlreadyInUseException", null, request.getLocale()), "CpfAlreadyInUse");
    return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
  }

  @ExceptionHandler({ Exception.class })
  public ResponseEntity<Object> handleInternal(final RuntimeException ex, final WebRequest request) {
    final GenericResponse bodyOfResponse = new GenericResponse(
        messages.getMessage("message.error", null, request.getLocale()), "InternalError");
    return new ResponseEntity<>(bodyOfResponse, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
