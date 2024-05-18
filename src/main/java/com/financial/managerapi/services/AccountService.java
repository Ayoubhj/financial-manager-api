package com.financial.managerapi.services;

import com.financial.managerapi.dto.AccountRequest;
import com.financial.managerapi.entities.Account;
import com.financial.managerapi.entities.Budget;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.exception.NotFoundException;
import com.financial.managerapi.interfaces.AccountInterface;
import com.financial.managerapi.interfaces.UserInterface;
import com.financial.managerapi.repositories.AccountRepository;
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
public class AccountService implements AccountInterface, UserInterface {

    private final AccountRepository accountRepository;
    private final BudgetRepository budgetRepository;

    @Override
    public Account findAccountById(UUID accountId) {
        return accountRepository.findById(accountId).orElseThrow(()
                -> new NotFoundException("account not found"));
    }

    @Override
    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

    @Override
    public Page<Account> findAllAccountsPageable(Pageable pageable) {
        return accountRepository.findAll(pageable);
    }


    @Override
    public Account createAccount(AccountRequest accountRequest) {

        Budget budget = budgetRepository.findById(accountRequest.getBudgetId()).orElseThrow(()
                -> new NotFoundException("budget not found"));

        var account = Account.builder()
                .accountName(accountRequest.getAccountName())
                .accountType(accountRequest.getAccountType())
                .balance(accountRequest.getBalance())
                .user(getConnectedUser())
                .budget(budget)
                .build();

        accountRepository.save(account);

        log.info("the account created successfully with name {}",account.getAccountName());

        return account;

    }

    @Override
    public Account updateAccount(AccountRequest accountRequest, UUID id) {

        var account = accountRepository.findById(id).orElseThrow(()
                -> new NotFoundException("account not found"));

        account = Account.builder()
                .accountName(accountRequest.getAccountName())
                .accountType(accountRequest.getAccountType())
                .balance(accountRequest.getBalance())
                .build();

        accountRepository.save(account);

        log.info("the account created updated with id {}", account.getAccountID());

        return account;
    }

    @Override
    public void deleteAccount(UUID id) {
          accountRepository.deleteById(id);
        log.info("the account created deleted with id {}", id );
    }

    @Override
    public User getConnectedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
