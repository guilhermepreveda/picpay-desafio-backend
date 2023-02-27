package com.payments_company.transactionsmanagement.dtos.user;

import com.payments_company.transactionsmanagement.enums.UserType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;

@Data
public class UserCreateDto {

  @NotNull(message = "Missing name: Name is required")
  @NotBlank(message = "Invalid name: Empty name")
  private String name;

  @NotNull(message = "Missing cpf: Cpf is required")
  @NotBlank(message = "Invalid cpf: Empty cpf")
  @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "Invalid cpf: Cpf format must be XXX.XXX.XXX-XX")
  private String cpf;

  @NotNull(message = "Missing email address: Email is required")
  @NotBlank(message = "Invalid email address: Empty email")
  @Email(message = "Invalid email address: Email is invalid", flags = { Flag.CASE_INSENSITIVE })
  private String email;

  @NotNull(message = "Missing password: Password is required")
  @NotBlank(message = "Invalid password: Empty password")
  private String password;

  @NotNull(message = "Missing user type: Type is required")
  private UserType type;

}
