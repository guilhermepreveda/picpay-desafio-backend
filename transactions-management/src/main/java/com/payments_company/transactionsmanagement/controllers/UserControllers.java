package com.payments_company.transactionsmanagement.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payments_company.transactionsmanagement.models.User;
import com.payments_company.transactionsmanagement.services.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserControllers {

  @Autowired
  UserServices userServices;

  @PostMapping
  public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
    User createdUser = userServices.createUser(user);

    return new ResponseEntity<User>(createdUser, HttpStatus.CREATED);
  }

  @PatchMapping
  public User updateUser(@Valid @RequestBody User user) {
    return userServices.updateUser(user);
  }

  @GetMapping
  public List<User> readAllUsers() {
    return userServices.readAllUsers();
  }

  @GetMapping("/{id}")
  public Optional<User> retrieveUser(@PathVariable("id") Long id) {
    return userServices.retrieveUser(id);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable("id") Long id) {
    userServices.deleteUser(id);
  }

}
