package com.payments_company.transactionsmanagement.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payments_company.transactionsmanagement.dtos.DepositCreateDto;
import com.payments_company.transactionsmanagement.dtos.user.UserCreateDto;
import com.payments_company.transactionsmanagement.exceptions.EmailAlreadyInUseException;
import com.payments_company.transactionsmanagement.exceptions.UserNotFoundException;
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

    boolean userExists = userRepository.findByEmail(userCreateDto.getEmail()) != null;

    if (userExists) {
      throw new EmailAlreadyInUseException();
    }

    final User user = new User();

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
    User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    float userOldBalance = foundUser.getBalance();

    foundUser.setBalance(userOldBalance + depositCreateDto.getValue());

    return userRepository.save(foundUser);
  }

  @Override
  public List<User> readAllUsers() {
    return userRepository.findAll();
  }

  @Override
  public User retrieveUser(Long id) {
    return userRepository.findById(id)
        .orElseThrow(() -> new UserNotFoundException());
  }

  @Override
  public void deleteUser(Long id) {
    User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    userRepository.delete(foundUser);
  }

}
