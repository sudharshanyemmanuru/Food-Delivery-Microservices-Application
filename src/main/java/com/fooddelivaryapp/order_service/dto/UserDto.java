package com.fooddelivaryapp.order_service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter@Setter@AllArgsConstructor@NoArgsConstructor
public class UserDto {
    private int userId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String userName;
    private String password;
    private String mailId;
}
