package com.payments_company.transactionsmanagement.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.payments_company.transactionsmanagement.persistence.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  User findByEmail(String email);

  User findByCpf(String cpf);

}