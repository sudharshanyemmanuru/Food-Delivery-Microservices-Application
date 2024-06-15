package com.fooddelivaryapp.order_service.mapper.impl;

import com.fooddelivaryapp.order_service.dto.OrderDto;
import com.fooddelivaryapp.order_service.entity.Order;
import com.fooddelivaryapp.order_service.mapper.OrderMapper;

public class OrderMapperImpl implements OrderMapper {
    @Override
    public OrderDto orderToOrderDto(Order order) {
        if(order==null)
            return null;
        OrderDto orderDto=new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setRestaurantDto(order.getRestaurantDto());
        orderDto.setFoodItemsDtos(order.getFoodItemsDtos());
        orderDto.setUserDto(order.getUserDto());
        return orderDto;
    }

    @Override
    public Order orderDtoToOrder(OrderDto orderDto) {
        if (orderDto==null)
            return null;
        Order order=new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setRestaurantDto(orderDto.getRestaurantDto());
        order.setFoodItemsDtos(orderDto.getFoodItemsDtos());
        order.setUserDto(orderDto.getUserDto());
        return order;
    }
}
