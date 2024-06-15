package com.fooddelivaryapp.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class FoodItemsDto {
    private int foodItemId;
    private String foodItemName;
    private String itemDescription;
    private boolean isVeg;
    private int price;
    private int restaurantId;
    private int quantity;
}
