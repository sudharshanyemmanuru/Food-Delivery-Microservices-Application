package com.fooddelivaryapp.order_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class OrderDetails {
    private int orderId;
    private List<FoodItemsDto> foodItemsDtos;
    private RestaurantDto restaurantDto;
    private UserDto userDto;
    private String emailTemplate;
}
