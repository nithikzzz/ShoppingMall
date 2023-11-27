package com.testprogramming.restapi;

import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertIterableEquals;
//import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import com.programming.restapi.Customer;
import com.programming.restapi.CustomerController;
import com.programming.restapi.CustomerNotFoundException;
import com.programming.restapi.CustomerRepository;
import com.programming.restapi.CustomerService;

import jakarta.inject.Inject;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = { AppConfig.class, DatabaseConfig.class}) 
public class ShoppingMallManageApplicationTests {

	@InjectMocks
	private CustomerController customerController;
	  
    @Mock
    private CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerService customerService;

    @Autowired
    private MyService myService;

	@Test
	 void testGetAllCustomers() {
		  // Arrange
       Customer customerToCreate = new Customer();
       customerToCreate.setId(1);
       customerToCreate.setName("Abc");
       customerToCreate.setOrder_Id(1);
       customerToCreate.setPhoneno("123");
       customerToCreate.setEmail("abc@example.com");

       // Act
       customerService.createCustomer(customerToCreate);

       // Assert
       verify(customerRepository, times(1)).save(any(Customer.class));
   }  
	   @Test
	    void testCreateCustomer() {
	        // Arrange
	        Customer customerToCreate = new Customer(1,"abc", 1, "123", "abc@example.com");

	        // Act
	        customerService.createCustomer(customerToCreate);

	        // Assert
	        verify(customerRepository, times(1)).save(customerToCreate);
	    }

	    @Test
	    void testUpdateCustomer() {
	        // Arrange
	        int customerId = 1;
	        Customer updatedCustomer = new Customer( customerId,"abc", 1, "123", "abc@example.com");
	        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer(customerId, "abc", 1, "123", "abc@example.com")));

	        // Act
	        Optional<Customer> actualUpdatedCustomer = customerService.updateCustomer(customerId, updatedCustomer);

	        // Assert
	        assertEquals(Optional.of(updatedCustomer), actualUpdatedCustomer);
	        verify(customerRepository, times(1)).save(updatedCustomer);
	    }

	    @Test
	    void testRemoveCustomer() {
	        // Arrange
	        int customerId = 1;
	        when(customerRepository.findById(customerId)).thenReturn(Optional.of(new Customer(customerId, "abc", 1, "123", "abc@example.com")));

	        // Act and Assert
	        assertThrows(CustomerNotFoundException.class, () -> customerService.removeCustomer(customerId));
	        verify(customerRepository, times(1)).deleteById(customerId);
	    }

    @Test
    public void testMyService() {
        // Write your test logic here
        // For example, if MyService has a method getSomeValue(), you can test it like this:
        String result = myService.greetUser("Balaji");

        // Assert the result
        assertEquals("Hello, Balaji!", result);
    }
}
