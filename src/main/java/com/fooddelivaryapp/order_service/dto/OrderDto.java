package com.fooddelivaryapp.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class OrderDto {
    private int orderId;
    private List<FoodItemsDto> foodItemsDtos;
    private RestaurantDto restaurantDto;
    private UserDto userDto;

}
