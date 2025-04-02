package com.br.MS_Payment.core.exceptions;

public class PaymentIdNotFound extends RuntimeException{
    public PaymentIdNotFound(String message) {
        super(message);
    }
}
