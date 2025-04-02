package com.br.MS_Payment.framework.domain;

import com.br.MS_Payment.core.enums.PaymentMethod;
import com.br.MS_Payment.core.enums.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_payment")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "paymentId")
public class Payment {

    public Payment(String orderId, BigDecimal amount, String email, Status status, PaymentMethod paymentMethod, String transactionId) {
        this.orderId = orderId;
        this.amount = amount;
        this.status = status;
        this.email = email;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.processedAt = LocalDateTime.now();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String paymentId;

    private String orderId;

    private BigDecimal amount;

    private String email;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    private String transactionId;

    private LocalDateTime processedAt;

}
