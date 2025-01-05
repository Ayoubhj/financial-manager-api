package com.financial.managerapi.services;

import com.financial.managerapi.dto.TransactionRequest;
import com.financial.managerapi.entities.Transaction;
import com.financial.managerapi.entities.User;
import com.financial.managerapi.exception.NotFoundException;
import com.financial.managerapi.interfaces.TransactionInterface;
import com.financial.managerapi.interfaces.UserInterface;
import com.financial.managerapi.repositories.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class TransactionService implements TransactionInterface, UserInterface {

    private final TransactionRepository transactionRepository;
    private final BudgetService budgetService;

    @Override
    public Transaction findTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction not found")
                );

    }

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Page<Transaction> findAllTransactionsPageable(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction createTransaction(TransactionRequest transactionRequest) {

        Transaction transaction = Transaction.builder()
                .name(transactionRequest.getName())
                .description(transactionRequest.getDescription())
                .amount(transactionRequest.getAmount())
                .budget(budgetService.findBudgetById(transactionRequest.getBudgetId()))
                .category(transactionRequest.getCategory())
                .type(transactionRequest.getTransactionType())
                .date(transactionRequest.getTransactionDate())
                .goal(transactionRequest.getGoal())
                .user(getConnectedUser())
                .build();

        return transactionRepository.save(transaction);

    }

    @Override
    public Transaction updateTransaction(TransactionRequest transactionRequest, Long id) {

        Transaction transaction = findTransactionById(id);

        transaction.setName(transactionRequest.getName());
        transaction.setDescription(transactionRequest.getDescription());
        transaction.setAmount(transactionRequest.getAmount());
        transaction.setBudget(budgetService.findBudgetById(transactionRequest.getBudgetId()));
        transaction.setCategory(transactionRequest.getCategory());
        transaction.setType(transactionRequest.getTransactionType());
        transaction.setDate(transactionRequest.getTransactionDate());
        transaction.setGoal(transactionRequest.getGoal());

        return  transactionRepository.save(transaction);

    }

    @Override
    public Transaction deleteTransaction(Long id) {

        Transaction transaction = findTransactionById(id);
        transactionRepository.delete(transaction);
        return transaction;

    }

    @Override
    public User getConnectedUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
