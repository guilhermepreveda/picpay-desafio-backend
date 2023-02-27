package com.payments_company.transactionsmanagement.services.impl;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payments_company.transactionsmanagement.dtos.DepositCreateDto;
import com.payments_company.transactionsmanagement.dtos.user.UserCreateDto;
import com.payments_company.transactionsmanagement.models.User;
import com.payments_company.transactionsmanagement.repositories.UserRepository;
import com.payments_company.transactionsmanagement.services.UserServices;

@Service
public class UserServicesImpl implements UserServices {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ModelMapper modelMapper;

  @Override
  public User createUser(UserCreateDto userCreateDto) {
    User postRequest = modelMapper.map(userCreateDto, User.class);

    return userRepository.save(postRequest);
  }

  @Override
  public User createDeposit(Long id, DepositCreateDto deposit) {
    Optional<User> foundUser = userRepository.findById(id);

    float userOldBalance = foundUser.get().getBalance();

    foundUser.get().setBalance(userOldBalance + deposit.getValue());

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
