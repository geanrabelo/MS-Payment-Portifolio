package com.br.MS_Payment.core.exceptions;

public class PaymentMethodNotExists extends RuntimeException {
    public PaymentMethodNotExists(String message) {
        super(message);
    }
}
