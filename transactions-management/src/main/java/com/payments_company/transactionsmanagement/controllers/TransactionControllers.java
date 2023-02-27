package com.payments_company.transactionsmanagement.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments_company.transactionsmanagement.dtos.TransactionCreateDto;
import com.payments_company.transactionsmanagement.models.Transaction;
import com.payments_company.transactionsmanagement.services.TransactionServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transactions")
public class TransactionControllers {

  @Autowired
  TransactionServices transactionServices;

  @PostMapping
  public ResponseEntity<Transaction> createUser(@Valid @RequestBody TransactionCreateDto transaction) {

    Transaction createdTransaction = transactionServices.createTransaction(transaction);

    return new ResponseEntity<Transaction>(createdTransaction, HttpStatus.CREATED);

  }

  @GetMapping("/{id}")
  public ResponseEntity<Optional<Transaction>> retrieveUser(@PathVariable("id") Long id) {
    Optional<Transaction> foundTransaction = transactionServices.retrieveTransaction(id);

    return new ResponseEntity<Optional<Transaction>>(foundTransaction, HttpStatus.OK);
  }

}
