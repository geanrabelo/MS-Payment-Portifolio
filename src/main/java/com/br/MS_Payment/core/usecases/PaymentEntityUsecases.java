package com.br.MS_Payment.core.usecases;

import com.br.MS_Payment.core.entity.PaymentEntity;

public interface PaymentEntityUsecases {
    void create(PaymentEntity paymentEntity);
    PaymentEntity findById(String paymentId);
    boolean existsById(String paymentId);
    PaymentEntity updateById(String paymentId, PaymentEntity newPaymentEntity);
}
