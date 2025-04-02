package com.br.MS_Payment.core.exceptions;

public class PaymentNotAllowed extends RuntimeException{
    public PaymentNotAllowed(String message){
        super(message);
    }
}
