package com.financial.managerapi.controller;

import com.financial.managerapi.dto.AccountRequest;
import com.financial.managerapi.dto.BudgetRequest;
import com.financial.managerapi.entities.Account;
import com.financial.managerapi.entities.Budget;
import com.financial.managerapi.services.AccountService;
import com.financial.managerapi.services.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/budget")
@RequiredArgsConstructor
public class BudgetController {

    private final BudgetService budgetService;

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Budget> findBudgetById(@PathVariable UUID id){
        return new ResponseEntity<Budget>(budgetService.findBudgetById(id), HttpStatus.OK );
    }

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Budget>> findAllBudgets(){
        return new ResponseEntity<List<Budget>>(budgetService.findAllBudgets(), HttpStatus.OK );
    }

    @RequestMapping(path = "/all/page",method = RequestMethod.GET)
    public ResponseEntity<Page<Budget>> findAllBudgetsPageable(Pageable pageable){
        return new ResponseEntity<Page<Budget>>(budgetService.findAllBudgetsPageable(pageable), HttpStatus.OK );
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<Budget> createAccount(@RequestBody BudgetRequest budgetRequest){
        return new ResponseEntity<Budget>(budgetService.createBudget(budgetRequest), HttpStatus.OK );
    }

    @RequestMapping(path = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Budget> updateAccount(@RequestBody BudgetRequest budgetRequest,@PathVariable UUID id){
        return new ResponseEntity<Budget>(budgetService.updateBudget(budgetRequest,id), HttpStatus.OK );
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable UUID id){
        budgetService.deleteBudget(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
