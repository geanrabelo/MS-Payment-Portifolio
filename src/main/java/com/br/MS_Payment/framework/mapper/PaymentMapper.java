package com.br.MS_Payment.framework.mapper;

import com.br.MS_Payment.core.entity.PaymentEntity;
import com.br.MS_Payment.framework.domain.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentEntity toPaymentEntity(Payment payment){
        return new PaymentEntity(payment.getOrderId(), payment.getAmount(), payment.getStatus(), payment.getPaymentMethod(), payment.getTransactionId());
    }

    public Payment toPayment(PaymentEntity paymentEntity){
        return new Payment(paymentEntity.getOrderId(), paymentEntity.getAmount(), paymentEntity.getStatus(), paymentEntity.getPaymentMethod(), paymentEntity.getTransactionId());
    }
}
