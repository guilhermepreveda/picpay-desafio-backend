package com.payments_company.transactionsmanagement.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.payments_company.transactionsmanagement.dto.DepositDto;
import com.payments_company.transactionsmanagement.dto.UserDto;
import com.payments_company.transactionsmanagement.exception.AppException;
import com.payments_company.transactionsmanagement.model.user.User;
import com.payments_company.transactionsmanagement.model.user.UserTypes;
import com.payments_company.transactionsmanagement.repository.UserRepository;
import com.payments_company.transactionsmanagement.service.UserServices;

@Service
public class UserServicesImpl implements UserServices {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public User createUser(final UserDto userDto) {

    boolean cpfInUse = userRepository.findByCpf(userDto.getCpf()) != null;
    if (cpfInUse) {
      throw new AppException("cpfAlreadyInUse", HttpStatus.CONFLICT);
    }

    boolean emailInUse = userRepository.findByEmail(userDto.getEmail()) != null;
    if (emailInUse) {
      throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
    }

    final User user = new User();

    user.setName(userDto.getName());
    user.setCpf(userDto.getCpf());
    user.setEmail(userDto.getEmail());

    user.setPassword(passwordEncoder.encode(userDto.getPassword()));

    user.setType(UserTypes.valueOf(userDto.getType()));

    return userRepository.save(user);

  }

  @Override
  public User updateUser(final Long id, final UserDto userDto) {

    User foundUser = userRepository.findById(id)
        .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

    foundUser.setName(userDto.getName());

    boolean cpfInUse = userRepository.findByCpf(userDto.getCpf()) != null;
    if (cpfInUse) {
      throw new AppException("cpfAlreadyInUse", HttpStatus.CONFLICT);
    }
    foundUser.setCpf(userDto.getCpf());

    boolean emailInUse = userRepository.findByEmail(userDto.getEmail()) != null;
    if (emailInUse) {
      throw new AppException("emailAlreadyInUse", HttpStatus.CONFLICT);
    }
    foundUser.setEmail(userDto.getEmail());

    foundUser.setPassword(passwordEncoder.encode(userDto.getPassword()));

    foundUser.setType(UserTypes.valueOf(userDto.getType()));

    return userRepository.save(foundUser);

  }

  @Override
  public User createDeposit(final Long id, final DepositDto depositDto) {

    User foundUser = userRepository.findById(id)
        .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

    float userOldBalance = foundUser.getBalance();

    foundUser.setBalance(userOldBalance + depositDto.getValue());

    return userRepository.save(foundUser);

  }

  @Override
  public List<User> readAllUsers() {

    return userRepository.findAll();

  }

  @Override
  public User retrieveUser(final Long id) {

    return userRepository.findById(id)
        .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

  }

  @Override
  public void deleteUser(final Long id) {

    User foundUser = userRepository.findById(id)
        .orElseThrow(() -> new AppException("userNotFound", HttpStatus.NOT_FOUND));

    userRepository.delete(foundUser);

  }

}
