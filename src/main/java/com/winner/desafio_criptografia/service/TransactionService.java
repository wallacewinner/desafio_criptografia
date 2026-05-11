package com.winner.desafio_criptografia.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.winner.desafio_criptografia.model.Transaction;

public class TransactionService {
    private final Queue<Transaction> transactionQueue = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction) {
        transactionQueue.add(transaction);
    }

    public void removeTransactionById(Long id) {
        transactionQueue.removeIf(transaction -> transaction.getId().equals(id));
    }

    public void updateTransactionValue(Long id, Long newValue) {
        transactionQueue.stream()
            .filter(transaction -> transaction.getId().equals(id))
            .findFirst()
            .ifPresent(transaction -> {
                transactionQueue.remove(transaction);
                transactionQueue.add(new Transaction(transaction.getId(), transaction.getUserDocument(), transaction.getCreditCardToken(), newValue));
            });
    }

    public void clearAllTransactions() {
        transactionQueue.clear();
    }
}
