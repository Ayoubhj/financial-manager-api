package com.financial.managerapi.interfaces;

import com.financial.managerapi.dto.GoalRequest;
import com.financial.managerapi.entities.Goal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GoalInterface {

    Goal findGoalById(Long id);

    List<Goal> findAllGoals();

    Page<Goal> findAllGoalsPageable(Pageable pageable);

    Goal createGoal(GoalRequest goalRequest);

    Goal updateGoal(GoalRequest goalRequest,Long id);

    Goal deleteGoal(Long id);


}
