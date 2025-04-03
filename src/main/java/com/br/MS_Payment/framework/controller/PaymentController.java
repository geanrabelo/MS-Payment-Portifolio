package com.br.MS_Payment.framework.controller;

import com.br.MS_Payment.core.entity.PaymentEntity;
import com.br.MS_Payment.core.usecases.PaymentEntityUsecases;
import com.br.MS_Payment.framework.dto.PaymentEvent;
import com.br.MS_Payment.framework.dto.PaymentMessageDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ms/payment")
public class PaymentController {

    @Autowired
    private PaymentEntityUsecases paymentEntityUsecases;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping
    @Transactional
    public ResponseEntity<?> sendForNotify(@RequestBody PaymentEvent paymentEvent){
        PaymentEntity paymentEntity = paymentEntityUsecases.findById(paymentEvent.paymentId());
        Map<String, Object> eventData = new HashMap<>();
        eventData.put("paymentId", paymentEntity.getPaymentId());
        eventData.put("orderId", paymentEntity.getOrderId());
        eventData.put("amount", paymentEntity.getAmount());
        eventData.put("email", paymentEntity.getEmail());
        eventData.put("transactionId", paymentEntity.getTransactionId());
        eventData.put("processedAt", paymentEntity.getProcessedAt());
        kafkaTemplate.send("payments", eventData);
        return ResponseEntity.ok(new PaymentMessageDTO("Payment Confirmed and send for Ms-Notify"));
    }
}
