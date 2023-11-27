package com.testprogramming.restapi;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.programming.restapi.Customer;
import com.programming.restapi.OrderDetails;
import com.programming.restapi.OrderDetailsRepository;
import com.programming.restapi.OrderDetailsService;

@SpringBootTest
public class OrderDetailsServiceTest {
	@Mock
	private OrderDetailsRepository orderDetailsRepository;
	@InjectMocks
	private OrderDetailsService orderDetailsService;
	
	@Test
	void testCreateOrderDetails() {
	    // Arrange
	    LocalDate dateOfPurchase = LocalDate.now();
	    int total = 100;
	    Customer customer = new Customer(1,"abc",1, "abc", "abc.@example.com");
	    String paymentMode = "Credit Card";
	    int shopId = 123;

	    OrderDetails orderDetailsToCreate = new OrderDetails(dateOfPurchase, total, customer, paymentMode, shopId);

	    // Act
	    orderDetailsRepository.save(orderDetailsToCreate);

	    // Assert
	    verify(orderDetailsRepository, times(1)).save(any(OrderDetails.class));
	}
	
	 @Test
	    void getOrderDetailsById() {
	        // Arrange
	        int orderId = 1;
	        OrderDetails expectedOrderDetails = new OrderDetails();
	        when(orderDetailsRepository.findById(orderId)).thenReturn(Optional.of(expectedOrderDetails));

	        // Act
	        Optional<OrderDetails> actualOrderDetails = orderDetailsService.getOrderDetailsById(orderId);

	        // Assert
	        assertEquals(expectedOrderDetails, actualOrderDetails);
	        verify(orderDetailsRepository, times(1)).findById(orderId);
	    }

	    @Test
	    void updateOrderDetails() {
	        // Arrange
	        int orderId = 1;
	        OrderDetails updatedOrderDetails = new OrderDetails();
	        when(orderDetailsRepository.findById(orderId)).thenReturn(Optional.of(new OrderDetails()));

	        // Act
	        orderDetailsService.updateOrderDetails(orderId, updatedOrderDetails);

	        // Assert
	        verify(orderDetailsRepository, times(1)).save(updatedOrderDetails);
	    }

	    @Test
	    void removeOrderDetails() {
	        // Arrange
	        int orderId = 1;
	        when(orderDetailsRepository.findById(orderId)).thenReturn(Optional.of(new OrderDetails()));

	        // Act
	        orderDetailsService.removeOrderDetails(orderId);

	        // Assert
	        verify(orderDetailsRepository, times(1)).deleteById(orderId);
	    }
	
	

}
























