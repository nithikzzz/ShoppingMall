package com.programming.restapi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailsRepository extends JpaRepository<OrderDetails, Integer> {
	 List<OrderDetails> findAllByCustomer_Id(int customerId);
}
