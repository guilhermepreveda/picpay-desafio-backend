package com.payments_company.transactionsmanagement.service;

import com.payments_company.transactionsmanagement.dto.TransactionDto;
import com.payments_company.transactionsmanagement.model.Transaction;

public interface TransactionServices {

  Transaction createTransaction(final TransactionDto transactionDto);

  Transaction retrieveTransaction(final Long id);

}
