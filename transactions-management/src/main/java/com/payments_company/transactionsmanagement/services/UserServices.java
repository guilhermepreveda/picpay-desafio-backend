package com.payments_company.transactionsmanagement.services;

import java.util.List;

import com.payments_company.transactionsmanagement.dtos.DepositDto;
import com.payments_company.transactionsmanagement.dtos.UserDto;
import com.payments_company.transactionsmanagement.models.User;

public interface UserServices {

  User createUser(UserDto userCreateDto);

  User updateUser(Long id, UserDto userDto);

  User createDeposit(Long id, DepositDto depositDto);

  List<User> readAllUsers();

  User retrieveUser(Long id);

  void deleteUser(Long id);

}
