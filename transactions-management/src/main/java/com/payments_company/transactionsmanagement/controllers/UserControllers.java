package com.payments_company.transactionsmanagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments_company.transactionsmanagement.dtos.DepositCreateDto;
import com.payments_company.transactionsmanagement.dtos.user.UserCreateDto;
import com.payments_company.transactionsmanagement.models.User;
import com.payments_company.transactionsmanagement.services.impl.UserServicesImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControllers {

  @Autowired
  UserServicesImpl userServices;

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody UserCreateDto userCreateDto) {

    User createdUser = userServices.createUser(userCreateDto);

    return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
  }

  @PostMapping("/{id}/deposit")
  public ResponseEntity<User> createUser(@PathVariable("id") Long id,
      @Valid @RequestBody DepositCreateDto depositCreateDto) {
    User depositedUser = userServices.createDeposit(id, depositCreateDto);

    return new ResponseEntity<User>(depositedUser, HttpStatus.OK);
  }

  @GetMapping
  public ResponseEntity<List<User>> readAllUsers() {
    List<User> allUsers = userServices.readAllUsers();

    return new ResponseEntity<List<User>>(allUsers, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<User> retrieveUser(@PathVariable("id") Long id) {
    User foundUser = userServices.retrieveUser(id);

    return new ResponseEntity<User>(foundUser, HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
    userServices.deleteUser(id);

    return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
  }

}
