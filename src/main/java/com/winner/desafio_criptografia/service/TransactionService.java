package com.winner.desafio_criptografia.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.stereotype.Service;

import com.winner.desafio_criptografia.model.Transaction;

import jakarta.websocket.Decoder.Text;

@Service
public class TransactionService {
    private final Queue<Transaction> transactionQueue = new ConcurrentLinkedQueue<>();
    
    public void addTransaction(Transaction transaction) {
        transaction.setCreditCardToken("123");;
        transaction.setUserDocument("123");;
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

    public java.util.List<Transaction> readAllTransactions() {
        return transactionQueue.stream().toList();
    }

    public void clearAllTransactions() {
        transactionQueue.clear();
    }

    private String encryptString(String text) {
        return "encrypted_" + creditCardToken;
    }
}
