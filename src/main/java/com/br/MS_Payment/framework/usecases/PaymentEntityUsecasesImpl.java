package com.br.MS_Payment.framework.usecases;

import com.br.MS_Payment.core.entity.PaymentEntity;
import com.br.MS_Payment.core.enums.EnumCode;
import com.br.MS_Payment.core.exceptions.PaymentIdNotFound;
import com.br.MS_Payment.core.usecases.PaymentEntityUsecases;
import com.br.MS_Payment.framework.domain.Payment;
import com.br.MS_Payment.framework.dto.ReceiveOrderEvent;
import com.br.MS_Payment.framework.mapper.PaymentMapper;
import com.br.MS_Payment.framework.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentEntityUsecasesImpl implements PaymentEntityUsecases {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void create(PaymentEntity paymentEntity) {
        Payment payment = paymentMapper.toPayment(paymentEntity);
        paymentRepository.save(payment);
    }

    @KafkaListener(topics = "orders", groupId = "payment-group")
    public void receiveEvent(ReceiveOrderEvent receiveOrderEvent){
        System.out.println("oi"+receiveOrderEvent);
    }

    @Override
    public PaymentEntity findById(String paymentId) {
        if(existsById(paymentId)){
            Payment payment = paymentRepository.getReferenceById(paymentId);
            return paymentMapper.toPaymentEntity(payment);
        }else{
            throw new PaymentIdNotFound(EnumCode.PAY0000.getMessage());
        }
    }

    @Override
    public boolean existsById(String paymentId) {
        return paymentRepository.existsById(paymentId);
    }

    @Override
    public PaymentEntity updateById(String paymentId, PaymentEntity newPaymentEntity) {
        if(paymentRepository.existsById(paymentId)){
            return null;
        }else{
            throw new PaymentIdNotFound(EnumCode.PAY0000.getMessage());
        }
    }
}
