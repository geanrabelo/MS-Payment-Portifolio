package com.br.MS_Payment.core.enums;

import com.br.MS_Payment.core.exceptions.PaymentMethodNotExists;

import java.util.stream.Stream;

public enum PaymentMethod {
    CREDIT_CARD,
    DEBIT_CARD,
    PIX;

    public static PaymentMethod method(int method) {
        return Stream.of(PaymentMethod.values())
                .filter(p -> p.ordinal() == method)
                .findAny()
                .orElseThrow(() -> new PaymentMethodNotExists(EnumCode.PAY0000.getMessage()));
    }
}
