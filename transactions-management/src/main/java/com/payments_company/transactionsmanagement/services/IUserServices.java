package com.payments_company.transactionsmanagement.services;

import java.util.List;
import java.util.Optional;

import com.payments_company.transactionsmanagement.models.User;

public interface IUserServices {
  User createUser(User user);

  User updateUser(User user);

  List<User> readAllUsers();

  Optional<User> retrieveUser(Long id);

  void deleteUser(Long id);
}
