package com.programming.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customers") // Set a base path for all endpoints in this controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // GET all customers
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    // GET a customer by ID
    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable int id) {
        Optional<Customer> optionalCustomer = Optional.ofNullable(customerService.getCustomerById(id));

        if (optionalCustomer.isPresent()) {
            return ResponseEntity.ok(optionalCustomer.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    // POST create a new customer
    @PostMapping("/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    // PUT update a customer by ID
    @PutMapping("/update/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer updatedCustomer) {
        return customerService.updateCustomer(id, updatedCustomer).orElse(null);
    }

    // DELETE remove a customer by ID
    @DeleteMapping("/delete/{id}")
    public void removeCustomer(@PathVariable int id) {
        customerService.removeCustomer(id);
    }
}
