package com.payments_company.transactionsmanagement.web.dtos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DepositDto {

  @NotNull(message = "Deposit value is required")
  @DecimalMin("0.01")
  private float value;

}
