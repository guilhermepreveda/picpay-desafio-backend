package com.payments_company.transactionsmanagement.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositCreateDto {

  @NotNull(message = "Missing transaction value: value is required")
  private float value;

}
