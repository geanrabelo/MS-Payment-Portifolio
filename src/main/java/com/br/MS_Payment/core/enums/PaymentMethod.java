package com.br.MS_Payment.core.enums;

import com.br.MS_Payment.core.exceptions.PaymentMethodNotExists;

public enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    PIX;

    public static PaymentMethod method(int method){
        switch (method){
            case 0:
                return PaymentMethod.CREDIT_CARD;
            case 1:
                return PaymentMethod.DEBIT_CARD;
            case 2:
                return PaymentMethod.PIX;
            default:
                throw new PaymentMethodNotExists(EnumCode.PAY0002.getMessage());
        }
    }
}
