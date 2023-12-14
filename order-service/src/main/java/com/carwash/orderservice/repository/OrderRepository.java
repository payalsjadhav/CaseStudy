package com.carwash.orderservice.repository;

import com.carwash.orderservice.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order,Integer> {

    @Query("{orderId:?0}")
    Order getOrderByOrderId(int orderId);

    @Query("{userName:?0}")
    List<Order> getOrdersByuserName(String userName);

    @Query("{orderStatus:?0}")
    List<Order> getOrdersByOrderStatus(String orderStatus);

    @Query("{washerName:?0}")
    List<Order> getByWasherName(String washerName);

    @Query(value = "{washerName:'?0'}",exists = true)
    Boolean existByName(String washerName);
}
