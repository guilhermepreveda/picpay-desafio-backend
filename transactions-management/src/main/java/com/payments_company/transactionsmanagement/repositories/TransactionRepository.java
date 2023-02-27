package com.payments_company.transactionsmanagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payments_company.transactionsmanagement.models.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}