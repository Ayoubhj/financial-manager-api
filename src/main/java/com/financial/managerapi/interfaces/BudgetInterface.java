package com.financial.managerapi.interfaces;

import com.financial.managerapi.dto.BudgetRequest;
import com.financial.managerapi.entities.Budget;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;


public interface BudgetInterface {

    Budget findBudgetById(Long BudgetId);

    List<Budget> findAllBudgets();

    Page<Budget> findAllBudgetsPageable(Pageable pageable);

    Budget createBudget(BudgetRequest budgetRequest);

    Budget updateBudget(BudgetRequest budgetRequest,Long id);

    void deleteBudget(Long id);

}
