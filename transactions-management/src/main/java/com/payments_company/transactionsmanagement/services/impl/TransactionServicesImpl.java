package com.payments_company.transactionsmanagement.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.payments_company.transactionsmanagement.dtos.MessageResponseDto;
import com.payments_company.transactionsmanagement.dtos.TransactionCreateDto;
import com.payments_company.transactionsmanagement.enums.UserType;
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
  public Transaction createTransaction(TransactionCreateDto transactionCreateDto) {
    float value = transactionCreateDto.getValue();

    Long payerId = transactionCreateDto.getPayer();
    Optional<User> foundPayer = userRepository.findById(payerId);
    if (!foundPayer.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Invalid payer: Payer not exists");
    }

    Long payeeId = transactionCreateDto.getPayee();
    Optional<User> foundPayee = userRepository.findById(payeeId);
    if (!foundPayee.isPresent()) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND,
          "Invalid payee: Payee not exists");
    }

    if (foundPayer.get().getType() == UserType.SELLER) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,
          "Invalid payer: SELLER type users cannot send money to other users");
    }

    if (foundPayer.get().getBalance() < value) {
      throw new ResponseStatusException(HttpStatus.FORBIDDEN,
          "Invalid transaction value: Payer balance is not sufficient");
    }

    RestTemplate restTemplate = new RestTemplate();
    MessageResponseDto transactionAuthorization = restTemplate.getForObject(
        "https://run.mocky.io/v3/8fafdd68-a090-496f-8c9a-3442cf30dae6",
        MessageResponseDto.class);

    if (transactionAuthorization == null
        || transactionAuthorization != null && !transactionAuthorization.getMessage().equals("Autorizado")) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
          "Authorization error: This transaction was not authorized");
    }

    float payerOldBalance = foundPayer.get().getBalance();
    float payeeOldBalance = foundPayee.get().getBalance();

    foundPayer.get().setBalance(payerOldBalance - value);
    foundPayee.get().setBalance(payeeOldBalance + value);

    userRepository.save(foundPayee.get());
    userRepository.save(foundPayer.get());

    Transaction createdTransaction = new Transaction(foundPayer.get(), foundPayee.get(), value);

    MessageResponseDto transactionEmailConfirmation = restTemplate.getForObject(
        "http://o4d9z.mocklab.io/notify",
        MessageResponseDto.class);

    if (transactionEmailConfirmation == null
        || transactionEmailConfirmation != null && !transactionEmailConfirmation.getMessage().equals("Success")) {
      foundPayer.get().setBalance(payerOldBalance + value);
      foundPayee.get().setBalance(payeeOldBalance - value);

      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,
          "Email confirmation error: The confirmation email was not sent and the sent values were reversed");
    }

    return transactionRepository.save(createdTransaction);
  }

  @Override
  public Optional<Transaction> retrieveTransaction(Long id) {
    return transactionRepository.findById(id);
  }

}
