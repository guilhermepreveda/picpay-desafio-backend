package com.payments_company.transactionsmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payments_company.transactionsmanagement.model.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(final String email);

  User findByCpf(final String cpf);

}