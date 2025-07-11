package com.github.diogocerqueiralima.authorizationserver.exceptions;

public class AgreementException extends RuntimeException {

    public AgreementException(String message) {
        super(message);
    }

    public AgreementException() {
        this("You should agree with the terms.");
    }

}
