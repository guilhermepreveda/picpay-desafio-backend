package com.payments_company.transactionsmanagement.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.payments_company.transactionsmanagement.enums.UserType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false, unique = true)
  private String cpf;

  @Column(nullable = false, unique = true)
  private String email;

  @Column(nullable = false)
  @JsonProperty(access = Access.WRITE_ONLY)

  private String password;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)

  private UserType type;

  @Column(nullable = false)
  @JsonProperty(access = Access.READ_ONLY)
  private float balance;

}