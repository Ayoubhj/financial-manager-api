package com.financial.managerapi.interfaces;

import com.financial.managerapi.dto.BudgetRequest;
import com.financial.managerapi.entities.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BudgetInterface {

    Budget findBudgetById(UUID BudgetId);

    List<Budget> findAllBudgets();

    Page<Budget> findAllBudgetsPageable(Pageable pageable);

    Budget createBudget(BudgetRequest BudgetRequest);

    Budget updateBudget(BudgetRequest BudgetRequest,UUID id);

    void deleteBudget(UUID id);

}
