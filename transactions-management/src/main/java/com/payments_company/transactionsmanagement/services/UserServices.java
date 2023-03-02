package com.payments_company.transactionsmanagement.services;

import java.util.List;

import com.payments_company.transactionsmanagement.persistence.models.User;
import com.payments_company.transactionsmanagement.web.dtos.DepositDto;
import com.payments_company.transactionsmanagement.web.dtos.UserDto;

public interface UserServices {

  User createUser(UserDto userCreateDto);

  User updateUser(Long id, UserDto userDto);

  User createDeposit(Long id, DepositDto depositDto);

  List<User> readAllUsers();

  User retrieveUser(Long id);

  void deleteUser(Long id);

}
