package com.financial.managerapi.dto;

import com.financial.managerapi.entities.Category;
import com.financial.managerapi.entities.Currency;
import com.financial.managerapi.entities.Goal;
import com.financial.managerapi.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    private String name;
    private String description;
    private Double amount;
    private Currency currency;
    private LocalDate transactionDate;
    private Category category;
    private TransactionType transactionType;
    private Long budgetId;
    private Goal goal;

}
