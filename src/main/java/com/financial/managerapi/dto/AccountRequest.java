package com.financial.managerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequest {

    private String accountName;

    private AccountType accountType;

    private double balance;

    private UUID budgetId;

}
