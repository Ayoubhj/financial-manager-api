package com.financial.managerapi.repositories;

import com.financial.managerapi.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Long> {
}
