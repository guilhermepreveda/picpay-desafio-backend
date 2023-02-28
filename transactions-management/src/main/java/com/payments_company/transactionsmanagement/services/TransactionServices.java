package com.payments_company.transactionsmanagement.services;

import com.payments_company.transactionsmanagement.dtos.TransactionCreateDto;
import com.payments_company.transactionsmanagement.models.Transaction;

public interface TransactionServices {

  Transaction createTransaction(TransactionCreateDto transactionCreateDto);

  Transaction retrieveTransaction(Long id);

}
