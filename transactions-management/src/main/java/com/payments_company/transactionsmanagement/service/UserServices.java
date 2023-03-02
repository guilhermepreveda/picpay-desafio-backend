package com.payments_company.transactionsmanagement.service;

import java.util.List;

import com.payments_company.transactionsmanagement.dto.DepositDto;
import com.payments_company.transactionsmanagement.dto.UserDto;
import com.payments_company.transactionsmanagement.model.user.User;

public interface UserServices {

  User createUser(final UserDto userCreateDto);

  User updateUser(final Long id, final UserDto userDto);

  User createDeposit(final Long id, final DepositDto depositDto);

  List<User> readAllUsers();

  User retrieveUser(final Long id);

  void deleteUser(final Long id);

}
