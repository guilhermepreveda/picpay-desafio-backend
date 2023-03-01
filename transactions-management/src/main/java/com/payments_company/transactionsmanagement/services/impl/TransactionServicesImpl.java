package com.payments_company.transactionsmanagement.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.payments_company.transactionsmanagement.dtos.MessageResponseDto;
import com.payments_company.transactionsmanagement.dtos.TransactionDto;
import com.payments_company.transactionsmanagement.enums.UserType;
import com.payments_company.transactionsmanagement.exceptions.BalanceNotSufficientException;
import com.payments_company.transactionsmanagement.exceptions.InvalidUserTypeException;
import com.payments_company.transactionsmanagement.exceptions.PayeeNotFoundException;
import com.payments_company.transactionsmanagement.exceptions.PayerNotFoundException;
import com.payments_company.transactionsmanagement.exceptions.TransactionNotAuthorizedException;
import com.payments_company.transactionsmanagement.exceptions.TransactionNotConfirmedException;
import com.payments_company.transactionsmanagement.exceptions.TransactionNotFoundException;
import com.payments_company.transactionsmanagement.models.Transaction;
import com.payments_company.transactionsmanagement.models.User;
import com.payments_company.transactionsmanagement.repositories.TransactionRepository;
import com.payments_company.transactionsmanagement.repositories.UserRepository;
import com.payments_company.transactionsmanagement.services.TransactionServices;

@Service
public class TransactionServicesImpl implements TransactionServices {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private UserRepository userRepository;

  @Override
  public Transaction createTransaction(TransactionDto transactionDto) {
    float value = transactionDto.getValue();

    Long payerId = transactionDto.getPayer();
    User foundPayer = userRepository.findById(payerId).orElseThrow(() -> new PayerNotFoundException());

    Long payeeId = transactionDto.getPayee();
    User foundPayee = userRepository.findById(payeeId).orElseThrow(() -> new PayeeNotFoundException());

    if (foundPayer.getType() == UserType.SELLER) {
      throw new InvalidUserTypeException();
    }

    if (foundPayer.getBalance() < value) {
      throw new BalanceNotSufficientException();
    }

    RestTemplate restTemplate = new RestTemplate();
    MessageResponseDto transactionAuthorization = restTemplate.getForObject(
        "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
        MessageResponseDto.class);

    if (transactionAuthorization == null
        || transactionAuthorization != null && !transactionAuthorization.getMessage().equals("Autorizado")) {
      throw new TransactionNotAuthorizedException();
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

      throw new TransactionNotConfirmedException();
    }

    return transactionRepository.save(createdTransaction);
  }

  @Override
  public Transaction retrieveTransaction(Long id) {
    return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException());
  }

}
