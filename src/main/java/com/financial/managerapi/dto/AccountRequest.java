package com.financial.managerapi.dto;

import com.financial.managerapi.entities.Budget;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.enums.AccountType;
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
