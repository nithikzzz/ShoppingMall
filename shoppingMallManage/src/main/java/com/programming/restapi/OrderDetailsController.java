package com.programming.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orderdetails")
public class OrderDetailsController {

    private final OrderDetailsService orderDetailsService;

    @Autowired
    public OrderDetailsController(OrderDetailsService orderDetailsService) {
        this.orderDetailsService = orderDetailsService;
    }

    @GetMapping
    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsService.getAllOrderDetails();
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderDetails> getOrderDetailsById(@PathVariable int orderId) {
        return orderDetailsService.getOrderDetailsById(orderId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/customer/{customerId}/orders")
    public List<OrderDetails> getOrdersForCustomer(@PathVariable int customerId) {
        return orderDetailsService.getOrdersForCustomer(customerId);
    }

    @PostMapping("/add")
    public ResponseEntity<Void> createOrderDetails(@RequestBody OrderDetails orderDetails) {
        orderDetailsService.createOrderDetails(orderDetails);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<OrderDetails> updateOrderDetails(@PathVariable int orderId, @RequestBody OrderDetails updateOrderDetails) {
        return orderDetailsService.updateOrderDetails(orderId, updateOrderDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<Void> removeOrderDetails(@PathVariable int orderId) {
        orderDetailsService.removeOrderDetails(orderId);
        return ResponseEntity.ok().build();
    }
}
