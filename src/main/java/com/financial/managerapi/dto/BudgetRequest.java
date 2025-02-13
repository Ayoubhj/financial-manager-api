package com.financial.managerapi.dto;

import com.financial.managerapi.entities.Currency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BudgetRequest {

    private String budgetName;
    private double budgetAmount;
    private LocalDate startDate;
    private LocalDate endDate;
    private Currency currency;

}
