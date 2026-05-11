package com.winner.desafio_criptografia.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TransactionDto {

    @NotNull
    private Long id;

    @NotBlank
    private String userDocument;

    @NotBlank
    private String creditCardToken;

    @NotBlank
    @Min(0)
    private Long value;

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
