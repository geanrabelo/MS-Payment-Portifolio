package com.br.MS_Payment.framework.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Component;

@Component
public class ReceiveOrderEvent {

    private Double totalValue;
    private Double balance;
    private int method;
    private String orderId;
    private Long customerId;
    private String eventType;
    private String email;

    @JsonProperty("totalValue")
    public Double getTotalValue() {
        return totalValue;
    }
    public void setTotalValue(Double totalValue) {
        this.totalValue = totalValue;
    }

    @JsonProperty("balance")
    public Double getBalance(){
        return this.balance;
    }
    public void setBalance(Double balance){
        this.balance = balance;
    }

    @JsonProperty("method")
    public int getMethod(){
        return this.method;
    }
    public void setMethod(int method){
        this.method = method;
    }

    @JsonProperty("orderId")
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    @JsonProperty("customerId")
    public Long getCustomerId() {
        return customerId;

    }
    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @JsonProperty("eventType")
    public String getEventType() {
        return eventType;

    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}
