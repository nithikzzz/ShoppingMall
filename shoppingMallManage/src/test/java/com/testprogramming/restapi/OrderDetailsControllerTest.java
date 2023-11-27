package com.testprogramming.restapi;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import com.programming.restapi.Customer;
import com.programming.restapi.OrderDetails;
import com.programming.restapi.OrderDetailsController;
import com.programming.restapi.OrderDetailsRepository;
import com.programming.restapi.OrderDetailsService;

public class OrderDetailsControllerTest {
	
	@Mock
	private OrderDetails orderDetails;
	
	private OrderDetailsRepository orderDetailsRepository;
	
	@Mock
	private OrderDetailsService orderDetailsService;
	
	@InjectMocks
	private OrderDetailsController orderDetailsController;
	
	void getAllOrderDetails() {
		  // Arrange
	    LocalDate dateOfPurchase = LocalDate.now();
	    int total = 100;
	    Customer customer = new Customer(1,"abc",1, "abc", "abc.@example.com");
	    String paymentMode = "Credit Card";
	    int shopId = 123;

	    OrderDetails expectedOrderDetails = new OrderDetails(dateOfPurchase, total, customer, paymentMode, shopId);

	    //Action
	    try {
	     orderDetailsRepository.save(expectedOrderDetails);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		//Assert
	    verify(orderDetailsRepository, times(1)).save(any(OrderDetails.class));
	}
	@Test
	void getOrderDetailsById() {
		// Arrange
		int orderId = 1;
		OrderDetails expectedOrderDetails = new OrderDetails();
		when(orderDetailsRepository.findById(orderId)).thenReturn(Optional.of(expectedOrderDetails));

		// Act
		ResponseEntity<OrderDetails> actualOrderDetails = orderDetailsController.getOrderDetailsById(orderId);

		// Assert
		assertEquals(expectedOrderDetails, actualOrderDetails);
		verify(orderDetailsRepository, times(1)).findById(orderId);
	}

	@Test
	void createOrderDetails() {
		// Arrange
		OrderDetails orderDetailsToCreate = new OrderDetails();

		// Act
		orderDetailsController.createOrderDetails(orderDetailsToCreate);

		// Assert
		verify(orderDetailsRepository, times(1)).save(orderDetailsToCreate);
	}

	@Test
	void updateOrderDetails() {
		// Arrange
		int orderId = 1;
		OrderDetails updatedOrderDetails = new OrderDetails();
		when(orderDetailsRepository.findById(orderId)).thenReturn(Optional.of(new OrderDetails()));

		// Act
		orderDetailsController.updateOrderDetails(orderId, updatedOrderDetails);

		// Assert
		verify(orderDetailsRepository, times(1)).save(updatedOrderDetails);
	}

	@Test
	void removeOrderDetails() {
		// Arrange
		int orderId = 1;
		when(orderDetailsRepository.findById(orderId)).thenReturn(Optional.of(new OrderDetails()));

		// Act
		orderDetailsController.removeOrderDetails(orderId);

		// Assert
		verify(orderDetailsRepository, times(1)).deleteById(orderId);
	}	
	
}
