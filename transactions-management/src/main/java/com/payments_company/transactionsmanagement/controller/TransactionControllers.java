package com.payments_company.transactionsmanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments_company.transactionsmanagement.dto.TransactionDto;
import com.payments_company.transactionsmanagement.model.Transaction;
import com.payments_company.transactionsmanagement.service.TransactionServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionControllers {

  @Autowired
  TransactionServices transactionServices;

  @PostMapping
  public ResponseEntity<Transaction> createUser(@Valid @RequestBody final TransactionDto transactionDto) {

    Transaction createdTransaction = transactionServices.createTransaction(transactionDto);

    return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.CREATED);

  }

  @GetMapping("/{id}")
  public ResponseEntity<Transaction> retrieveUser(@PathVariable("id") final Long id) {

    Transaction foundTransaction = transactionServices.retrieveTransaction(id);

    return new ResponseEntity<Transaction>(foundTransaction, HttpStatus.OK);

  }

}
