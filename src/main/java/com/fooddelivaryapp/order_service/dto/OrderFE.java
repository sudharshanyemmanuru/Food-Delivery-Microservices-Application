package com.fooddelivaryapp.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderFE {
    private List<FoodItemsDto> foodItemsDtos;
    private int userId;
    private RestaurantDto restaurantDto;
    private String emailTemplate;
}
