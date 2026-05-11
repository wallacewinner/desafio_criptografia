package com.winner.desafio_criptografia.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.winner.desafio_criptografia.dto.TransactionDto;
import com.winner.desafio_criptografia.model.Transaction;
import com.winner.desafio_criptografia.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionDto request) {
        transactionService.addTransaction(new Transaction(
            request.getId().longValue(),
            request.getUserDocument(),
            request.getCreditCardToken(),
            request.getValue().longValue()
        ));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> readTransactions() {
        List<Transaction> transactions = transactionService.readAllTransactions();
        return ResponseEntity.ok(transactions);
    }

    @PostMapping("/update")
    public ResponseEntity<Void> updateTransaction(@Valid @RequestBody TransactionDto request) {
        transactionService.updateTransactionValue(request.getId(), request.getValue().longValue());
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteTransactions(@RequestBody TransactionDto request) {
        transactionService.removeTransactionById(request.getId());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
