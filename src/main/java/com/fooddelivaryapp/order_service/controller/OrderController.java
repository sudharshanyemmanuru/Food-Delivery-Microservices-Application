package com.fooddelivaryapp.order_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fooddelivaryapp.order_service.dto.OrderDto;
import com.fooddelivaryapp.order_service.dto.OrderFE;
import com.fooddelivaryapp.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/orders",produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ObjectMapper objectMapper;
    @PostMapping("/place")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody OrderFE orderFE) {
        OrderDto orderDto=orderService.placeOrder(orderFE);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDto);
    }
    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable int orderId){
        return orderService.getOrder(orderId);
    }
    @GetMapping("/order/{userId}")
    public ResponseEntity<List<OrderDto>> fetchByUserId(@PathVariable int userId){
        List<OrderDto> orderDtoList=orderService.fetchByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(orderDtoList);
    }
    @GetMapping("/send")
    public ResponseEntity<String> send(){
        orderService.send();
        return ResponseEntity.status(HttpStatus.OK).body("send success!!");
    }
}
