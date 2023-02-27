package com.payments_company.transactionsmanagement.services;

import java.util.Optional;

import com.payments_company.transactionsmanagement.dtos.TransactionCreateDto;
import com.payments_company.transactionsmanagement.models.Transaction;

public interface TransactionServices {

  Transaction createTransaction(TransactionCreateDto transactionCreateDto);

  Optional<Transaction> retrieveTransaction(Long id);

}
