package com.carwash.orderservice.controller;

import com.carwash.orderservice.model.Order;
import com.carwash.orderservice.model.OrderAccept;
import com.carwash.orderservice.model.OrderDetails;
import com.carwash.orderservice.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @PostMapping("/place-order")
    public ResponseEntity<String> createOrder(@RequestBody Order order){
       int id= orderService.placeOrder(order);
        return new ResponseEntity<>("placed order,order id "+id, HttpStatus.OK);
    }

    @GetMapping("/userorders/{userName}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable String userName){
      List<Order> orders=  orderService.getOrdersByUserName(userName);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/washerorders/{userName}")
    public ResponseEntity<List<Order>> getWasherOrders(@PathVariable String userName){
        List<Order> orders=  orderService.getOrdersByWasherName(userName);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @DeleteMapping("/deleteOrder/{orderId}")
    public ResponseEntity<String> deleteOrder(@PathVariable int orderId){
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>("order deleted",HttpStatus.OK);
    }
    @PutMapping("/cancelorder")
    public ResponseEntity<String> cancelOrder(@RequestBody Order order){
        int orserId=orderService.cancelOrder(order);
        return new ResponseEntity<>("canclled order with id"+ orserId,HttpStatus.OK);
    }

    @PutMapping("/updateorder")
    public ResponseEntity<String> updateOrder(@RequestBody Order order){
         orderService.updateOrder(order);
        return new ResponseEntity<>("order updated",HttpStatus.OK);
    }

    @GetMapping("/allorders")
    public ResponseEntity<List<Order>> getAllOrders(){
       List<Order> orderList= orderService.getAllOrders();
        return new ResponseEntity<>(orderList,HttpStatus.OK);
    }

    @GetMapping("/getorder/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable int orderId){
       Order order= orderService.getOrderById(orderId);
       return new ResponseEntity<>(order,HttpStatus.OK);
    }

    @PutMapping("/acceptorder")
    public ResponseEntity<Order> acceptOrder(@RequestBody OrderAccept order){
        Order order1=orderService.acceptOrder(order);
        return new ResponseEntity<>(order1,HttpStatus.OK);
    }

    @GetMapping("/getorders/{orderStatus}")
   public ResponseEntity<List<Order>> getOrdersByOrderStatus(@PathVariable String orderStatus){
        List<Order> orders=orderService.getOrdersByOrderStatus(orderStatus);
        return new ResponseEntity<>(orders,HttpStatus.OK);
   }

   @GetMapping("/orderdetail/{orderId}")
    public ResponseEntity<OrderDetails> getOrderDetails(@PathVariable int orderId){
        OrderDetails orderDetails= orderService.getOrderDetails(orderId);
        return new ResponseEntity<>(orderDetails,HttpStatus.OK);
   }

}
