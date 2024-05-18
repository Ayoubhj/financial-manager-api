package com.financial.managerapi.interfaces;

import com.financial.managerapi.dto.AccountRequest;
import com.financial.managerapi.entities.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface AccountInterface {

    Account findAccountById(UUID accountId);
    List<Account> findAllAccounts();
    Page<Account> findAllAccountsPageable(Pageable pageable);
    Account createAccount(AccountRequest accountRequest);

    Account updateAccount(AccountRequest accountRequest,UUID id);

    void deleteAccount(UUID id);

}
