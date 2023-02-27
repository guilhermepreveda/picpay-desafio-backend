package com.payments_company.transactionsmanagement.services;

import java.util.List;

import com.payments_company.transactionsmanagement.dtos.DepositCreateDto;
import com.payments_company.transactionsmanagement.dtos.user.UserCreateDto;
import com.payments_company.transactionsmanagement.models.User;

public interface UserServices {

  User createUser(UserCreateDto userCreateDto);

  User createDeposit(Long id, DepositCreateDto deposit);

  List<User> readAllUsers();

  User retrieveUser(Long id);

  void deleteUser(Long id);

}
