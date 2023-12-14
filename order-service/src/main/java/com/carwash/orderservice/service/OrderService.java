package com.carwash.orderservice.service;

import com.carwash.orderservice.model.Order;
import com.carwash.orderservice.model.OrderAccept;

import java.util.List;

public interface OrderService {

    int placeOrder(Order order);
    void updateOrder(Order order);
    void deleteOrder(int orderId);
    List<Order> getAllOrders();
    Order getOrderById(int orderId);
    int cancelOrder(Order order);
    List<Order> getOrdersByUserName(String userName);
    List<Order> getOrdersByOrderStatus(String orderStatus);
    List<Order> getOrdersByWasherName(String washerName);
    Order acceptOrder(OrderAccept order);
}
