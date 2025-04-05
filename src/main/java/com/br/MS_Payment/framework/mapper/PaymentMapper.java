package com.br.MS_Payment.framework.mapper;

import com.br.MS_Payment.core.entity.PaymentEntity;
import com.br.MS_Payment.framework.domain.Payment;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentMapper {

    public PaymentEntity toPaymentEntity(Payment payment){
        return PaymentEntity.PaymentBuilder.builder()
                .orderId(payment.getOrderId())
                .amount(payment.getAmount())
                .email(payment.getEmail())
                .status(payment.getStatus())
                .paymentMethod(payment.getPaymentMethod())
                .transactionId(payment.getTransactionId())
                .build();
    }

    public Payment toPayment(PaymentEntity paymentEntity){
        return Payment.builder()
                .orderId(paymentEntity.getOrderId())
                .amount(paymentEntity.getAmount())
                .email(paymentEntity.getEmail())
                .status(paymentEntity.getStatus())
                .paymentMethod(paymentEntity.getPaymentMethod())
                .transactionId(paymentEntity.getTransactionId())
                .processedAt(LocalDateTime.now())
                .build();
    }
}
