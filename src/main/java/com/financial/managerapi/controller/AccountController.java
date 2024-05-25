package com.financial.managerapi.controller;

import com.financial.managerapi.dto.AccountRequest;
import com.financial.managerapi.entities.Account;
import com.financial.managerapi.services.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<Account> findAccountById(@PathVariable UUID id){
        return new ResponseEntity<Account>(accountService.findAccountById(id), HttpStatus.OK );
    }

    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<Account>> findAllAccounts(){
        return new ResponseEntity<List<Account>>(accountService.findAllAccounts(), HttpStatus.OK );
    }

    @RequestMapping(path = "/all/page",method = RequestMethod.GET)
    public ResponseEntity<Page<Account>> findAllAccounts(Pageable pageable){
        return new ResponseEntity<Page<Account>>(accountService.findAllAccountsPageable(pageable), HttpStatus.OK );
    }

    @RequestMapping(path = "/create",method = RequestMethod.POST)
    public ResponseEntity<Account> createAccount(@RequestBody AccountRequest accountRequest){
        return new ResponseEntity<Account>(accountService.createAccount(accountRequest), HttpStatus.OK );
    }

    @RequestMapping(path = "/update/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccount(@RequestBody AccountRequest accountRequest,@PathVariable UUID id){
        return new ResponseEntity<Account>(accountService.updateAccount(accountRequest,id), HttpStatus.OK );
    }

    @RequestMapping(path = "/delete/{id}",method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteAccount(@PathVariable UUID id){
        accountService.deleteAccount(id);
        return new ResponseEntity<Account>(HttpStatus.OK);
    }

}
