package com.financial.managerapi.dto;

import com.financial.managerapi.entities.Category;
import com.financial.managerapi.entities.Currency;
import com.financial.managerapi.enums.GoalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GoalRequest {

    private String name;
    private String description;
    private Double targetAmount;
    private LocalDate targetDate;
    private GoalType type;
    private Category category;
    private Currency currency;

}
