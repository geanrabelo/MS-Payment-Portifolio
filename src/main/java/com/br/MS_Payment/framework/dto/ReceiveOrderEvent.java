package com.br.MS_Payment.framework.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ReceiveOrderEvent(String eventType,
                                String orderId,
                                String customerId,
                                String email,
                                BigDecimal totalValue) {
    @JsonCreator
    public ReceiveOrderEvent(
            @JsonProperty("eventType") String eventType,
            @JsonProperty("orderId") String orderId,
            @JsonProperty("customerId") String customerId,
            @JsonProperty("email") String email,
            @JsonProperty("totalValue") BigDecimal totalValue
    ) {
        this.eventType = eventType;
        this.orderId = orderId;
        this.customerId = customerId;
        this.email = email;
        this.totalValue = totalValue;
    }
}
