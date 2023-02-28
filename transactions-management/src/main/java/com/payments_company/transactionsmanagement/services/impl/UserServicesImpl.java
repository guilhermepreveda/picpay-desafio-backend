package com.payments_company.transactionsmanagement.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payments_company.transactionsmanagement.dtos.DepositCreateDto;
import com.payments_company.transactionsmanagement.dtos.user.UserCreateDto;
import com.payments_company.transactionsmanagement.exceptions.EmailAlreadyInUseException;
import com.payments_company.transactionsmanagement.models.User;
import com.payments_company.transactionsmanagement.repositories.UserRepository;
import com.payments_company.transactionsmanagement.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User createUser(UserCreateDto userCreateDto) {

    if (userRepository.findByEmail(userCreateDto.getEmail()) != null) {
      throw new EmailAlreadyInUseException("Invalid email address: Email is already in use");
    }

    User user = new User();

    user.setName(userCreateDto.getName());
    user.setCpf(userCreateDto.getCpf());
    user.setCpf(userCreateDto.getCpf());
    user.setEmail(userCreateDto.getEmail());

    user.setPassword(passwordEncoder.encode(userCreateDto.getPassword()));

    user.setType(userCreateDto.getType());

    return userRepository.save(user);
  }

  @Override
  public User createDeposit(Long id, DepositCreateDto depositCreateDto) {
    Optional<User> foundUser = userRepository.findById(id);

    float userOldBalance = foundUser.get().getBalance();

    foundUser.get().setBalance(userOldBalance + depositCreateDto.getValue());

    return userRepository.save(foundUser.get());
  }

  @Override
  public List<User> readAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User retrieveUser(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Invalid user id: User not found"));
  }

  @Override
  public void deleteUser(Long id) {
    userRepository.deleteById(id);
  }

}
