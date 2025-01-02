package com.financial.managerapi.services;

import com.financial.managerapi.dto.BudgetRequest;
import com.financial.managerapi.entities.Budget;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.exception.NotFoundException;
import com.financial.managerapi.interfaces.BudgetInterface;
import com.financial.managerapi.interfaces.UserInterface;
import com.financial.managerapi.repositories.BudgetRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
@Slf4j
public class BudgetService implements BudgetInterface, UserInterface {

    private final BudgetRepository budgetRepository;


    @Override
    public Budget findBudgetById(UUID BudgetId) {
        return budgetRepository.findById(BudgetId).orElseThrow(()
                -> new NotFoundException("Budget not found"));
    }

    @Override
    public List<Budget> findAllBudgets() {
        return budgetRepository.findAll();
    }

    @Override
    public Page<Budget> findAllBudgetsPageable(Pageable pageable) {
        return budgetRepository.findAll(pageable);
    }

    @Override
    public Budget createBudget(BudgetRequest BudgetRequest) {
        return null;
    }

    @Override
    public Budget updateBudget(BudgetRequest BudgetRequest, UUID id) {
        return null;
    }

    @Override
    public void deleteBudget(UUID id) {

    }


    @Override
    public User getConnectedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
