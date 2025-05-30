package com.br.MS_Payment.core.entity;

import com.br.MS_Payment.core.enums.PaymentMethod;
import com.br.MS_Payment.core.enums.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PaymentEntity {


    public PaymentEntity(String orderId, BigDecimal amount, String email,Status status, PaymentMethod paymentMethod, String transactionId) {
        this.paymentId = UUID.randomUUID().toString();
        this.orderId = orderId;
        this.amount = amount;
        this.email = email;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.transactionId = transactionId;
        this.processedAt = LocalDateTime.now();
    }

    private PaymentEntity(){}

    private String paymentId;
    private String orderId;
    private BigDecimal amount;
    private Status status;
    private PaymentMethod paymentMethod;
    private String transactionId;
    private String email;
    private LocalDateTime processedAt;


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PaymentEntity that = (PaymentEntity) o;
        return paymentId.equals(that.paymentId);
    }

    @Override
    public int hashCode() {
        return paymentId.hashCode();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public static class PaymentBuilder {
        private String paymentId;
        private String orderId;
        private BigDecimal amount;
        private Status status;
        private PaymentMethod paymentMethod;
        private  String transactionId;
        private String email;
        private LocalDateTime processedAt;

        public static PaymentBuilder builder(){
            return new PaymentBuilder();
        }

        public PaymentBuilder paymentId(String paymentId){
            this.paymentId = paymentId;
            return this;
        }
        public PaymentBuilder orderId(String orderId){
            this.orderId = orderId;
            return this;
        }
        public PaymentBuilder amount(BigDecimal amount){
            this.amount = amount;
            return this;
        }
        public PaymentBuilder status(Status status){
            this.status = status;
            return this;
        }
        public PaymentBuilder paymentMethod(PaymentMethod paymentMethod){
            this.paymentMethod = paymentMethod;
            return this;
        }
        public PaymentBuilder transactionId(String transactionId){
            this.transactionId = transactionId;
            return this;
        }
        public PaymentBuilder email(String email){
            this.email = email;
            return this;
        }
        public PaymentBuilder processedAt(){
            this.processedAt = LocalDateTime.now();
            return this;
        }

        public PaymentEntity build(){
            return new PaymentEntity(this.orderId, this.amount, this.email, this.status, this.paymentMethod, this.transactionId);
        }
    }
}
