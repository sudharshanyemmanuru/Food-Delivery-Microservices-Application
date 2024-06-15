package com.fooddelivaryapp.order_service.controller;

import com.fooddelivaryapp.order_service.dto.TransactionDetails;
import com.fooddelivaryapp.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/payment")
public class PaymentController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/{amount}")
    public ResponseEntity<TransactionDetails> createTransaction(@PathVariable(name = "amount") int amount){
        System.out.println("creating transaction.."+amount);
        TransactionDetails transactionDetails=orderService.createTransaction(amount);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(transactionDetails);
    }
}
