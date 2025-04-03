package com.br.MS_Payment.framework.controller;

import com.br.MS_Payment.core.entity.PaymentEntity;
import com.br.MS_Payment.core.usecases.PaymentEntityUsecases;
import com.br.MS_Payment.framework.dto.PaymentEvent;
import com.br.MS_Payment.framework.dto.PaymentMessageDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/ms/payment")
public class PaymentController {

    @Autowired
    private PaymentEntityUsecases paymentEntityUsecases;

    @GetMapping
    public ResponseEntity<?> getById(@RequestParam(value = "paymentId") String paymentId){
        return ResponseEntity.ok(paymentEntityUsecases.findById(paymentId));
    }
}
