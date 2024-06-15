package com.fooddelivaryapp.order_service.service;

import com.fooddelivaryapp.order_service.dto.*;
import com.fooddelivaryapp.order_service.entity.Order;
import com.fooddelivaryapp.order_service.mapper.OrderMapper;
import com.fooddelivaryapp.order_service.repository.OrderRepository;
import com.razorpay.RazorpayClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.json.JSONObject;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private StreamBridge streamBridge;
    private final String KEY="rzp_test_KEhOaq0xLJNpwZ";
    private final String SECRET_KEY="0xwt61hCmtdI9D9OzvYG46Lt";
    private final String CURRENCY="INR";
    public OrderDto placeOrder(OrderFE orderFE){
        Order orderTobePlaced=new Order();
        orderTobePlaced.setFoodItemsDtos(orderFE.getFoodItemsDtos());
        orderTobePlaced.setRestaurantDto(orderFE.getRestaurantDto());
        orderTobePlaced.setOrderId(sequenceGeneratorService.generateNextOrderId());
        orderTobePlaced.setUserDto(getUserDetails(orderFE.getUserId()));
        OrderDetails details=orderDetails(orderTobePlaced);
        System.out.println("Email Template : "+orderFE.getEmailTemplate());
        details.setEmailTemplate(orderFE.getEmailTemplate());
        log.info("sending communication .... ");
        var res=streamBridge.send("sendCommunication-out-0",details);
        log.info("communication send : "+res);
        return OrderMapper.INSTANCE.orderToOrderDto(orderRepository.save(orderTobePlaced));
    }
    public ResponseEntity<OrderDto> getOrder(int id){
        Optional<Order> order=orderRepository.findById(id);
        if(order.isEmpty())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        OrderDto dto=OrderMapper.INSTANCE.orderToOrderDto(order.get());

        return ResponseEntity.status(HttpStatus.OK).body(dto);
    }
    private UserDto getUserDetails(int userId){
        return restTemplate.getForObject("http://USER-DETAILS-SERVICE/api/users/"+userId,UserDto.class);
    }
    public List<OrderDto> fetchByUserId(int userId){
        List<Order> orders=orderRepository.findAll();
        List<OrderDto> orderDtoList=orders.stream().map(OrderMapper.INSTANCE::orderToOrderDto).toList();
        return orderDtoList;
    }
    public void send(){
        OrderDetails details=new OrderDetails();
        //details.setMailId("sudharshanyemmanuru@gmail.com");
        log.info("sending communication .... ");
        var res=streamBridge.send("sendCommunication-out-0",details);
        log.info("communication send : "+res);
    }
    public TransactionDetails createTransaction(int amount){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("amount",(amount*100));
        jsonObject.put("currency",CURRENCY);
        try{
            RazorpayClient razorpayClient=new RazorpayClient(KEY,SECRET_KEY);

            com.razorpay.Order order=razorpayClient.orders.create(jsonObject);
            TransactionDetails transactionDetails=TransactionDetails.builder()
                    .orderId(order.get("id"))
                    .amount(order.get("amount"))
                    .currency(CURRENCY)
                    .key(KEY).build();
            return transactionDetails;
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return null;
    }
    private OrderDetails orderDetails(Order order){
        OrderDetails orderDetails=new OrderDetails();
        orderDetails.setFoodItemsDtos(order.getFoodItemsDtos());
        orderDetails.setUserDto(order.getUserDto());
        orderDetails.setRestaurantDto(order.getRestaurantDto());
        return orderDetails;
    }
}
