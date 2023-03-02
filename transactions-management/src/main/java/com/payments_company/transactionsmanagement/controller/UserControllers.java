package com.payments_company.transactionsmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments_company.transactionsmanagement.dto.DepositDto;
import com.payments_company.transactionsmanagement.dto.UserDto;
import com.payments_company.transactionsmanagement.model.user.User;
import com.payments_company.transactionsmanagement.service.impl.UserServicesImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControllers {

  @Autowired
  UserServicesImpl userServices;

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody final UserDto userDto) {

    User createdUser = userServices.createUser(userDto);

    return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable("id") final Long id,
      @Valid @RequestBody final UserDto userDto) {
    User updatedUser = userServices.updateUser(id, userDto);

    return new ResponseEntity<User>(updatedUser, HttpStatus.OK);
  }

  @PostMapping("/{id}/deposit")
  public ResponseEntity<User> createUser(@PathVariable("id") final Long id,
      @Valid @RequestBody final DepositDto depositDto) {
    User depositedUser = userServices.createDeposit(id, depositDto);

    return new ResponseEntity<User>(depositedUser, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<User>> readAllUsers() {
    List<User> allUsers = userServices.readAllUsers();

    return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> retrieveUser(@PathVariable("id") final Long id) {
    User foundUser = userServices.retrieveUser(id);

    return new ResponseEntity<User>(foundUser, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable("id") final Long id) {
    userServices.deleteUser(id);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

}
