package com.carwash.orderservice.service;

import com.carwash.orderservice.exceptions.NotFoundException;
import com.carwash.orderservice.exceptions.UserNameException;
import com.carwash.orderservice.model.*;
import com.carwash.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SequenceGeneratorService seqService;

    @Autowired
    private UserService userService;
    @Autowired
    private WasherService washerService;

    @Override
    public int placeOrder(Order order) {
        String userUrl="http://localhost:8081/users/exist/"+order.getUserName();
       Boolean user=restTemplate.getForObject(userUrl,Boolean.class);
       if (!user)
           throw new UserNameException("incorrect userName");
       order.setOrderId(seqService.getSequenceNumber(Order.SEQUENCE_NAME));
       order.setWasherName("NA");
       order.setOrderStatus("open");
       order.setPlacedOn(new Date());
       orderRepository.save(order);
     return order.getOrderId();
    }

    @Override
    public void deleteOrder(int orderId) {
        if(!orderRepository.existsById(orderId))
            throwNotFoundException("Order");
        orderRepository.deleteById(orderId);
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orders=orderRepository.findAll();
        if(orders.size()==0)
            throwNotFoundException("Order");
        return orders;
    }

    @Override
    public Order getOrderById(int orderId) {
        if(!orderRepository.existsById(orderId))
            throwNotFoundException("Order");
        return orderRepository.getOrderByOrderId(orderId);
    }

    @Override
    public int cancelOrder(Order order) {
       if(orderRepository.existsById(order.getOrderId())){
           Order order1=orderRepository.findById(order.getOrderId()).get();
           order1.setOrderStatus("cancelled");
       }else {
           throwNotFoundException("Order");
       }
       return order.getOrderId();
    }



    @Override
    public List<Order> getOrdersByUserName(String userName) {
        List<Order> orderList = orderRepository.getOrdersByuserName(userName);
        if(orderList.size()==0)
            throwNotFoundException("Order");
        return orderList;
    }
    @Override
    public List<Order> getOrdersByWasherName(String washerName){
        Boolean washer=orderRepository.existByName(washerName);
        if(!washer)
            throw new UserNameException("washer not found");
        List<Order> orders=orderRepository.getByWasherName(washerName);
        return orders;
    }

    @Override
    public Order  acceptOrder(OrderAccept order) {
        boolean isOrderExists=orderRepository.existsById(order.getOrderId());
         Order order1=orderRepository.getOrderByOrderId(order.getOrderId());
        if(isOrderExists)
            order1.setWasherName(order.getWasherName());
            order1.setOrderStatus("accepted");
        return orderRepository.save(order1);
    }

    @Override
    public List<Order> getOrdersByOrderStatus(String orderStatus){
        List<Order> orderList = orderRepository.getOrdersByOrderStatus(orderStatus);
        if(orderList.size()==0)
            throwNotFoundException("Order");
        return orderList;
    }

    @Override
    public void updateOrder(Order order) {
        if (!existsById(order.getOrderId()))
            throwNotFoundException("Order");
          placeOrder(order);
    }

    public void throwNotFoundException(String s) {
        throw new NotFoundException(s+" doesnot exists...");
    }
    public boolean existsById(int orderId) {
        return orderRepository.existsById(orderId);
    }

    public OrderDetails getOrderDetails(int orderId){
        Order order=orderRepository.getOrderByOrderId(orderId);
        OrderDetails orderDetails=new OrderDetails();

        orderDetails.setOrderId(order.getOrderId());
        orderDetails.setCity(order.getCity());
        orderDetails.setStreet(order.getStreet());
        orderDetails.setPincode(order.getPincode());
        orderDetails.setCarBrand(order.getCarBrand());
        orderDetails.setModel(order.getModel());
        orderDetails.setColor(order.getColor());
        orderDetails.setPackageName(order.getPackageName());
        orderDetails.setOrderStatus(order.getOrderStatus());
        orderDetails.setTotalPrice(order.getTotalPrice());
        orderDetails.setPlacedOn(order.getPlacedOn());

        UserDtos userDtos = userService.getUser(order.getUserName());

        orderDetails.setUserName(userDtos.getUserName());
        orderDetails.setFullName(userDtos.getFullName());
        orderDetails.setPhoneNo(userDtos.getPhoneNo());
        orderDetails.setEmail(userDtos.getEmail());

        WasherDto washerDto= washerService.getWasher(order.getWasherName());
        orderDetails.setWasherName(washerDto.getUserName());
        orderDetails.setWasherphoneNo(washerDto.getPhoneNo());
        orderDetails.setWasheremail(washerDto.getEmail());
        orderDetails.setWasherFullName(washerDto.getFullName());

        return orderDetails;

    }

}


