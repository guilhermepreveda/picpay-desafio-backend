package com.payments_company.transactionsmanagement.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;

@Entity
@Table(name = "users")
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull(message = "Missing name: Name is required")
  @NotBlank(message = "Invalid name: Empty name")
  private String name;

  @Column(unique = true)
  @NotNull(message = "Missing cpf: Cpf is required")
  @NotBlank(message = "Invalid cpf: Empty cpf")
  @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "Invalid cpf: Cpf format must be XXX.XXX.XXX-XX")
  private String cpf;

  @Column(unique = true)
  @NotNull(message = "Missing email address: Email is required")
  @NotBlank(message = "Invalid email address: Empty email")
  @Email(message = "Invalid email address: Email is invalid", flags = { Flag.CASE_INSENSITIVE })
  private String email;

  @NotNull(message = "Missing password: Password is required")
  @NotBlank(message = "Invalid password: Empty password")
  @JsonProperty(access = Access.WRITE_ONLY)
  private String password;

  @NotNull(message = "Missing user type: Type is required")
  @NotBlank(message = "Invalid user type: Empty type")
  private String type;

  private float balance;

  public User() {
  }

  public User(Long id, String name, String cpf, String email, String password, String type, float balance) {
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.email = email;
    this.password = password;
    this.type = type;
    this.balance = balance;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public float getBalance() {
    return balance;
  }

  public void setBalance(float balance) {
    this.balance = balance;
  }

}
