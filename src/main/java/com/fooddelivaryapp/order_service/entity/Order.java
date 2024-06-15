package com.fooddelivaryapp.order_service.entity;

import com.fooddelivaryapp.order_service.dto.FoodItemsDto;
import com.fooddelivaryapp.order_service.dto.RestaurantDto;
import com.fooddelivaryapp.order_service.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("order")
public class Order {
    @Id
    private int orderId;
    private List<FoodItemsDto> foodItemsDtos;
    private RestaurantDto restaurantDto;
    private UserDto userDto;
}
