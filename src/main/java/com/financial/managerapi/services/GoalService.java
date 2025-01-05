package com.financial.managerapi.services;

import com.financial.managerapi.dto.GoalRequest;
import com.financial.managerapi.entities.Goal;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.exception.NotFoundException;
import com.financial.managerapi.interfaces.GoalInterface;
import com.financial.managerapi.interfaces.UserInterface;
import com.financial.managerapi.repositories.GoalRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoalService implements GoalInterface, UserInterface {

    private final GoalRepository goalRepository;

    @Override
    public Goal findGoalById(Long id) {
        return goalRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Goal not found")
        );
    }

    @Override
    public List<Goal> findAllGoals() {
        return goalRepository.findAll();
    }

    @Override
    public Page<Goal> findAllGoalsPageable(Pageable pageable) {
        return goalRepository.findAll(pageable);
    }

    @Override
    public Goal createGoal(GoalRequest goalRequest) {

        Goal goal = Goal.builder()
                .name(goalRequest.getName())
                .type(goalRequest.getType())
                .user(getConnectedUser())
                .targetAmount(goalRequest.getTargetAmount())
                .targetDate(goalRequest.getTargetDate())
                .currency(goalRequest.getCurrency())
                .build();

        return goalRepository.save(goal);

    }

    @Override
    public Goal updateGoal(GoalRequest goalRequest, Long id) {

        Goal goal = findGoalById(id);

        goal.setName(goalRequest.getName());
        goal.setType(goalRequest.getType());
        goal.setTargetAmount(goalRequest.getTargetAmount());
        goal.setTargetDate(goalRequest.getTargetDate());
        goal.setCurrency(goalRequest.getCurrency());

        return goalRepository.save(goal);

    }

    @Override
    public Goal deleteGoal(Long id) {
        Goal goal = findGoalById(id);
        goalRepository.delete(goal);
        return goal;
    }

    @Override
    public User getConnectedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
