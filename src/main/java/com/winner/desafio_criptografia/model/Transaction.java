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
    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUserDocument() {
        return userDocument;
    }
    public void setUserDocument(String userDocument) {
        this.userDocument = userDocument;
    }
    public Long getValue() {
        return value;
    }
}
