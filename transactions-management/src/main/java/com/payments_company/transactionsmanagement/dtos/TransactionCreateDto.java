package com.payments_company.transactionsmanagement.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TransactionCreateDto {

  @NotNull(message = "Missing payer id: Payer id is required")
  private Long payer;

  @NotNull(message = "Missing payee id: Payee id is required")
  private Long payee;

  @NotNull(message = "Missing transaction value: value is required")
  private float value;

}
