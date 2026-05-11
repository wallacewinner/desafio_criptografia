package com.winner.desafio_criptografia.model;

public class Transaction {

    private Long id;
    private String userDocument;
    private String creditCardToken;
    private Long value;

    public Transaction(Long id, String userDocument, String creditCardToken, Long value) {
        this.id = id;
        this.userDocument = userDocument;
        this.creditCardToken = creditCardToken;
        this.value = value;
    }

    public String getCreditCardToken() {
        return creditCardToken;
    }
    public Long getId() {
        return id;
    }
    public String getUserDocument() {
        return userDocument;
    }
    public Long getValue() {
        return value;
    }
}
