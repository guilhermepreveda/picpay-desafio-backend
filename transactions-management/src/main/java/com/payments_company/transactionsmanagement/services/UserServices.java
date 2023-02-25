package com.payments_company.transactionsmanagement.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments_company.transactionsmanagement.models.User;
import com.payments_company.transactionsmanagement.repositories.UserRepository;

@Service
public class UserServices implements IUserServices {

  @Autowired
  private UserRepository userRepository;

  @Override
  public User createUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public User updateUser(User user) {
    return userRepository.save(user);
  }

  @Override
  public List<User> readAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public Optional<User> retrieveUser(Long id) {
    return userRepository.findById(id);
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

}
