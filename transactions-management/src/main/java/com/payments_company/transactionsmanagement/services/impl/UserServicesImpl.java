package com.payments_company.transactionsmanagement.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payments_company.transactionsmanagement.dtos.DepositDto;
import com.payments_company.transactionsmanagement.dtos.UserDto;
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
  public User createUser(UserDto userDto) {

    boolean userExists = userRepository.findByEmail(userDto.getEmail()) != null;

    if (userExists) {
      throw new EmailAlreadyInUseException();
    }

    final User user = new User();

    user.setName(userDto.getName());
    user.setCpf(userDto.getCpf());
    user.setEmail(userDto.getEmail());

    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

    user.setType(userDto.getType());

    return userRepository.save(user);
  }

  @Override
  public User updateUser(Long id, UserDto userDto) {

    User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    foundUser.setName(userDto.getName());
    foundUser.setCpf(userDto.getCpf());
    foundUser.setEmail(userDto.getEmail());

    foundUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

    foundUser.setType(userDto.getType());

    return userRepository.save(foundUser);
  }

  @Override
  public User createDeposit(Long id, DepositDto depositDto) {
    User foundUser = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException());

    float userOldBalance = foundUser.getBalance();

    foundUser.setBalance(userOldBalance + depositDto.getValue());

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
