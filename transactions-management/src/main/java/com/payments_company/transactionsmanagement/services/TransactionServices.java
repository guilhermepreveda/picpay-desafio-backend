package com.payments_company.transactionsmanagement.services;

import com.payments_company.transactionsmanagement.persistence.models.Transaction;
import com.payments_company.transactionsmanagement.web.dtos.TransactionDto;

public interface TransactionServices {

  Transaction createTransaction(TransactionDto transactionDto);

  Transaction retrieveTransaction(Long id);

}
