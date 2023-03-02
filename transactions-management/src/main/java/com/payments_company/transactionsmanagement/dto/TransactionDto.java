package com.payments_company.transactionsmanagement.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionDto {

  @NotNull(message = "Payer id is required")
  private Long payer;

  @NotNull(message = "Payee id is required")
  private Long payee;

  @NotNull(message = "Transaction value is required")
  @DecimalMin("0.01")
  private float value;

}
