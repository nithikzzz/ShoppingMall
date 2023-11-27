package com.programming.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class OrderDetailsService {

    private final OrderDetailsRepository orderDetailsRepository;

    
    public OrderDetailsService(OrderDetailsRepository orderDetailsRepository) {
        this.orderDetailsRepository = orderDetailsRepository;
    }

    public List<OrderDetails> getAllOrderDetails() {
        return orderDetailsRepository.findAll();
    }

    public Optional<OrderDetails> getOrderDetailsById(int orderId) {
        return Optional.ofNullable(orderDetailsRepository.findById(orderId)
        		.orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + orderId)));
    }

    @Transactional
    public void createOrderDetails(OrderDetails orderDetails) {
        orderDetailsRepository.save(orderDetails);
    }

    @Transactional
    public Optional<OrderDetails> updateOrderDetails(int orderId, OrderDetails updatedOrderDetails) {
        return getOrderDetailsById(orderId)
                .map(existingOrderDetails -> {
                    existingOrderDetails.setDateOfPurchase(updatedOrderDetails.getDateOfPurchase());
                    existingOrderDetails.setTotal(updatedOrderDetails.getTotal());
                    existingOrderDetails.setCustomer(updatedOrderDetails.getCustomer());
                    existingOrderDetails.setPaymentMode(updatedOrderDetails.getPaymentMode());
                    existingOrderDetails.setShopId(updatedOrderDetails.getShopId());
                    return orderDetailsRepository.save(existingOrderDetails);
                });
    }

    @Transactional
    public void removeOrderDetails(int orderId) {
        getOrderDetailsById(orderId).ifPresent(orderDetailsRepository::delete);
    }

    public List<OrderDetails> getOrdersForCustomer(int customerId) {
        return orderDetailsRepository.findAllByCustomer_Id(customerId);
    }
}
