package com.financial.managerapi.repositories;

import com.financial.managerapi.entities.Budget;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BudgetRepository extends JpaRepository<Budget, Long> {


}
