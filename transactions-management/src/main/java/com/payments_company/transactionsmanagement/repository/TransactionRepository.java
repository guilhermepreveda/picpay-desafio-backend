package com.payments_company.transactionsmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payments_company.transactionsmanagement.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}