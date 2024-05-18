package com.financial.managerapi.repositories;

import com.financial.managerapi.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface AccountRepository extends JpaRepository<Account, UUID> {

    @Query(value = "SELECT SUM(balance) FROM Account WHERE budget_id = :budgetId ", nativeQuery = true)
    double getSumOffBalanceByBudgetId(@Param("budgetId") UUID budgetId);

}
