package com.payments_company.transactionsmanagement.services;

import com.payments_company.transactionsmanagement.dtos.TransactionDto;
import com.payments_company.transactionsmanagement.models.Transaction;

public interface TransactionServices {

  Transaction createTransaction(TransactionDto transactionDto);

  Transaction retrieveTransaction(Long id);

}
