package com.fooddelivaryapp.order_service.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class TransactionDetails {
    private String orderId;
    private Integer amount;
    private String currency;
    private String key;

}