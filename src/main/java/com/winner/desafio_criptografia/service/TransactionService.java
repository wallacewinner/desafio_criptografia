package com.winner.desafio_criptografia.service;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;
import org.springframework.stereotype.Service;

import com.winner.desafio_criptografia.model.Transaction;


@Service
public class TransactionService {
    private final Queue<Transaction> transactionQueue = new ConcurrentLinkedQueue<>();
    private final String SALT = KeyGenerators.string().generateKey();
    
    public void addTransaction(Transaction transaction, String authentication) {
        if (authentication != null) {
            transaction.setUserDocument(encryptString(transaction.getUserDocument(), authentication));
            transaction.setCreditCardToken(encryptString(transaction.getCreditCardToken(), authentication));
        }
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

    public java.util.List<Transaction> readAllTransactions(String authentication) {
        return transactionQueue.stream()
            .map(transaction -> {
                if (authentication != null) {
                    return new Transaction(
                        transaction.getId(),
                        decryptString(transaction.getUserDocument(), authentication),
                        decryptString(transaction.getCreditCardToken(), authentication),
                        transaction.getValue()
                    );
                } else {
                    return transaction;
                }
            })
            .toList();
    }

    public void clearAllTransactions() {
        transactionQueue.clear();
    }

    public String encryptString(String text, String secretPassword) {
        TextEncryptor encryptor = Encryptors.text(secretPassword, SALT);
        return encryptor.encrypt(text);
    }

    private String decryptString(String encryptedText, String secretPassword) {
        TextEncryptor encryptor = Encryptors.text(secretPassword, SALT);
        return encryptor.decrypt(encryptedText);
    }
}
