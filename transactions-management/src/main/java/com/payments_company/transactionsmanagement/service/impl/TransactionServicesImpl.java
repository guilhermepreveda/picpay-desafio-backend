package com.payments_company.transactionsmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payments_company.transactionsmanagement.dto.MessageResponseDto;
import com.payments_company.transactionsmanagement.dto.TransactionDto;
import com.payments_company.transactionsmanagement.exception.AppException;
import com.payments_company.transactionsmanagement.model.Transaction;
import com.payments_company.transactionsmanagement.model.user.User;
import com.payments_company.transactionsmanagement.model.user.UserTypes;
import com.payments_company.transactionsmanagement.repository.TransactionRepository;
import com.payments_company.transactionsmanagement.repository.UserRepository;
import com.payments_company.transactionsmanagement.service.TransactionServices;

@Service
public class TransactionServicesImpl implements TransactionServices {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Transaction createTransaction(final TransactionDto transactionDto) {

    float value = transactionDto.getValue();

    Long payerId = transactionDto.getPayer();
    User foundPayer = userRepository.findById(payerId)
        .orElseThrow(() -> new AppException("payerNotFound", HttpStatus.NOT_FOUND));

    Long payeeId = transactionDto.getPayee();
    User foundPayee = userRepository.findById(payeeId)
        .orElseThrow(() -> new AppException("payeeNotFound", HttpStatus.NOT_FOUND));

    if (foundPayer.getType() == UserTypes.SELLER) {
      throw new AppException("invalidUserType", HttpStatus.FORBIDDEN);
    }

    if (foundPayer.getBalance() < value) {
      throw new AppException("balanceNotSufficient", HttpStatus.FORBIDDEN);
    }

    RestTemplate restTemplate = new RestTemplate();
    MessageResponseDto transactionAuthorization = restTemplate.getForObject(
        "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
        MessageResponseDto.class);

    if (transactionAuthorization == null
        || transactionAuthorization != null && !transactionAuthorization.getMessage().equals("Autorizado")) {
      throw new AppException("transactionNotAuthorized", HttpStatus.UNAUTHORIZED);
    }

    float payerOldBalance = foundPayer.getBalance();
    float payeeOldBalance = foundPayee.getBalance();

    foundPayer.setBalance(payerOldBalance - value);
    foundPayee.setBalance(payeeOldBalance + value);

    userRepository.save(foundPayee);
    userRepository.save(foundPayer);

    Transaction createdTransaction = new Transaction(foundPayer, foundPayee, value);

    MessageResponseDto transactionEmailConfirmation = restTemplate.getForObject(
        "http://o4d9z.mocklab.io/notify",
        MessageResponseDto.class);

    if (transactionEmailConfirmation == null
        || transactionEmailConfirmation != null && !transactionEmailConfirmation.getMessage().equals("Success")) {
      foundPayer.setBalance(payerOldBalance + value);
      foundPayee.setBalance(payeeOldBalance - value);

      throw new AppException("transactionNotConfirmed", HttpStatus.FAILED_DEPENDENCY);

    }

    return transactionRepository.save(createdTransaction);

  }

  @Override
  public Transaction retrieveTransaction(final Long id) {

    return transactionRepository.findById(id)
        .orElseThrow(() -> new AppException("transactionNotFound", HttpStatus.NOT_FOUND));

  }

}
