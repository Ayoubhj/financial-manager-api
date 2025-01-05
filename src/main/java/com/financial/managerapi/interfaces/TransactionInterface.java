package com.financial.managerapi.interfaces;

import com.financial.managerapi.dto.TransactionRequest;
import com.financial.managerapi.entities.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionInterface {

    Transaction findTransactionById(Long id);

    List<Transaction> findAllTransactions();

    Page<Transaction> findAllTransactionsPageable(Pageable pageable);

    Transaction createTransaction(TransactionRequest transactionRequest);

    Transaction updateTransaction(TransactionRequest transactionRequest, Long id);

    Transaction deleteTransaction(Long id);

}
