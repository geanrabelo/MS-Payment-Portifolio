package com.br.MS_Payment.framework.usecases;

import com.br.MS_Payment.core.entity.PaymentEntity;
import com.br.MS_Payment.core.enums.EnumCode;
import com.br.MS_Payment.core.enums.PaymentMethod;
import com.br.MS_Payment.core.enums.Status;
import com.br.MS_Payment.core.exceptions.PaymentIdNotFound;
import com.br.MS_Payment.core.usecases.PaymentEntityUsecases;
import com.br.MS_Payment.framework.domain.Payment;
import com.br.MS_Payment.framework.dto.ReceiveOrderEvent;
import com.br.MS_Payment.framework.mapper.PaymentMapper;
import com.br.MS_Payment.framework.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentEntityUsecasesImpl implements PaymentEntityUsecases {

    @Autowired
    private PaymentMapper paymentMapper;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @Override
    public String create(PaymentEntity paymentEntity) {
        Payment payment = paymentMapper.toPayment(paymentEntity);
        return paymentRepository.save(payment).getPaymentId();
    }

    @KafkaListener(topics = "orders", groupId = "payment-group")
    public void receiveEvent(ReceiveOrderEvent receiveOrderEvent){
        if(validatePayment(receiveOrderEvent)){
            String paymentId = create(new PaymentEntity(receiveOrderEvent.getOrderId(), BigDecimal.valueOf(receiveOrderEvent.getTotalValue()), receiveOrderEvent.getEmail(), Status.APPROVED, PaymentMethod.method(receiveOrderEvent.getMethod()), UUID.randomUUID().toString()));

            Payment payment = paymentRepository.findById(paymentId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));
            Map<String, Object> eventData = new HashMap<>();
            eventData.put("paymentId", payment.getPaymentId());
            eventData.put("orderId", payment.getOrderId());
            eventData.put("amount", payment.getAmount());
            eventData.put("email", payment.getEmail());
            eventData.put("transactionId", payment.getTransactionId());
            eventData.put("processedAt", payment.getProcessedAt());
            kafkaTemplate.send("payments", eventData);
        }else{
            create(new PaymentEntity(receiveOrderEvent.getOrderId(), BigDecimal.valueOf(receiveOrderEvent.getTotalValue()), receiveOrderEvent.getEmail(), Status.REJECTED, PaymentMethod.method(receiveOrderEvent.getMethod()), UUID.randomUUID().toString()));
        }
    }

    private boolean validatePayment(ReceiveOrderEvent receiveOrderEvent){
        if(receiveOrderEvent.getBalance() >= receiveOrderEvent.getTotalValue()){
            return true;
        }else{
            return false;
        }
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
