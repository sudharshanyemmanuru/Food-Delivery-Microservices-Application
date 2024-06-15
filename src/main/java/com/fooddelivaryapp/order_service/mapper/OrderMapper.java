package com.fooddelivaryapp.order_service.mapper;

import com.fooddelivaryapp.order_service.dto.OrderDto;
import com.fooddelivaryapp.order_service.entity.Order;
import com.fooddelivaryapp.order_service.mapper.impl.OrderMapperImpl;

public interface OrderMapper {
    public OrderMapper INSTANCE=new OrderMapperImpl();
    public OrderDto orderToOrderDto(Order order);
    public Order orderDtoToOrder(OrderDto orderDto);
}
