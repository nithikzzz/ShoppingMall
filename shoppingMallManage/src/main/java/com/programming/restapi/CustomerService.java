package com.programming.restapi;

import java.util.List;
import java.util.Optional;

import org.hibernate.type.TrueFalseConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
@Transactional   // <-- Add @Transactional at the class level
public class CustomerService {
	
	private final CustomerRepository customerRepository;
	
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
		// TODO Auto-generated constructor stub
	}
	
	@Transactional
	  public List<Customer> getAllCustomers() {
	     return customerRepository.findAll();
	    }
	@Transactional
	 public Customer getCustomerById(int id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + id));
	}
	 @Transactional
	    public void createCustomer(Customer customer) {
	        customerRepository.save(customer);
	    }
	 
	 @Transactional
	 public Optional<Customer> updateCustomer(int id, Customer updatedCustomer) {
	        Optional<Customer> existingCustomer = customerRepository.findById(id);

	        if (existingCustomer.isPresent()) {
	            Customer customer = existingCustomer.get();
	            customer.setName(updatedCustomer.getName());
	            customer.setOrder_Id(updatedCustomer.getOrder_Id());
	            customer.setPhoneno(updatedCustomer.getPhoneno());
	            customer.setEmail(updatedCustomer.getEmail());

	            customerRepository.save(customer);
	        }

	        return existingCustomer;
	    }
	 @Transactional
	 public void removeCustomer(int id) {
	        if (customerRepository.existsById(id)) {
	            customerRepository.deleteById(id);
	        } else {
	            throw new CustomerNotFoundException("Customer not found with id: " + id);
	        }
	    }
}
