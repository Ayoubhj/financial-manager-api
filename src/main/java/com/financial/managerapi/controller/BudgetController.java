package com.financial.managerapi.controller;

import com.financial.managerapi.dto.BudgetRequest;
import com.financial.managerapi.entities.Budget;
import com.financial.managerapi.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Budget> findBudgetById(@PathVariable Long id){
        return new ResponseEntity<>(budgetService.findBudgetById(id), HttpStatus.OK );
    }

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Budget>> findAllBudgets(){
        return new ResponseEntity<>(budgetService.findAllBudgets(), HttpStatus.OK );
    }

    @RequestMapping(path = "/all/page",method = RequestMethod.GET)
    public ResponseEntity<Page<Budget>> findAllBudgetsPageable(Pageable pageable){
        return new ResponseEntity<>(budgetService.findAllBudgetsPageable(pageable), HttpStatus.OK );
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<Budget> createAccount(@RequestBody BudgetRequest budgetRequest){
        return new ResponseEntity<>(budgetService.createBudget(budgetRequest), HttpStatus.OK );
    }

    @RequestMapping(path = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Budget> updateAccount(@RequestBody BudgetRequest budgetRequest,@PathVariable Long id){
        return new ResponseEntity<>(budgetService.updateBudget(budgetRequest,id), HttpStatus.OK );
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable Long id){
        budgetService.deleteBudget(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
