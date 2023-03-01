package com.payments_company.transactionsmanagement.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Pattern.Flag;
import lombok.Data;

@Data
public class UserDto {

  @NotNull(message = "Name is required")
  @NotBlank(message = "Name is empty")
  private String name;

  @NotNull(message = "Cpf is required")
  @NotBlank(message = "Cpf is empty")
  @Pattern(regexp = "^\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}$", message = "Cpf format must be XXX.XXX.XXX-XX")
  private String cpf;

  @NotNull(message = "Email is required")
  @NotBlank(message = "Email is empty")
  @Email(message = "Email format is invalid", flags = { Flag.CASE_INSENSITIVE })
  private String email;

  @NotNull(message = "Password is required")
  @NotBlank(message = "Password is empty")
  private String password;

  @NotNull(message = "User type is required")
  @NotBlank(message = "User type is empty")
  @Pattern(regexp = "(COMMON|SELLER)", message = "User type must be COMMON or SELLER")
  private String type;

}
